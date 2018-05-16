package com.jinhui.scheduler.web.api.controller.product;

import com.jinhui.scheduler.biz.cmbfae.service.product.CmbfaeProductService;
import com.jinhui.scheduler.biz.core.product.service.ChnProductService;
import com.jinhui.scheduler.biz.core.product.service.ProductDividedService;
import com.jinhui.scheduler.biz.core.product.service.ProductService;
import com.jinhui.scheduler.biz.core.product.service.TermPaymentService;
import com.jinhui.scheduler.biz.core.util.ProductUtils;
import com.jinhui.scheduler.data.core.mapper.core.*;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.core.*;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.web.api.vo.base.SimpleWebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 产品添加查看修改
 *
 * @author jinhui
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ChnProductService chnProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ChnProductDao chnProductDao;

    @Autowired
    private ChnInfoDao chnInfoDao;


    @Autowired
    private TermPaymentScheduleMapper termPaymentScheduleMapper;


    @Autowired
    private TermPaymentService termPaymentService;

    @Autowired
    private ProductDividedService productDividedService;

    @Autowired
    private ExchangeInfoMapper exchangeInfoDao;


    @Autowired
    private CmbfaeProductService cmbfaeProductService;

    @Autowired
    private com.jinhui.scheduler.biz.cmbfae.service.product.divided.ProductDividedService cmbfaeProductDividedService;

    /**
     * 本地导入产品要素表返回产品信息
     */
    @RequestMapping(value = "/uploadProductFile", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult uploadProductFile(@RequestParam("type") String type, @RequestParam("productNo") String productNo, MultipartFile fileContent) {

        if (type == null || type.equals("")) {
            throw new BizException("产品类型不能为空");
        }

        if (fileContent == null) {
            throw new BizException("找不到上传文件，请重新上传");
        }
        InputStream inputStream = null;
        try {
            inputStream = fileContent.getInputStream();
        } catch (IOException e) {
            throw new BizException("文件上传失败，请联系管理员");
        }

        ProductVo product = productService.resolveProductFile(inputStream, type);


        //验证解析出来的产品代码和产品类型是否与前端输入的一致
        SimpleWebResult result;
        if (product.getProductNo().equals(productNo)) {
            result = SimpleWebResult.ok();
            result.setData(product);
        } else {
            result = SimpleWebResult.error("产品要素表内的产品代码与输入不一致");
        }

        return result;


    }


    /**
     * 通过文件更新产品信息,同时更新到“渠道产品表”，“定期产品回款计划表”
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult updateProduct(@RequestBody ProductVo productVo) {

        ProductVo p = productDao.selectAllInfoByProductNo(productVo.getProductNo());
        if (p == null) {
            SimpleWebResult error = SimpleWebResult.error("系统中没有该产品:" + productVo.getProductNo());
            return error;
        }

        productDao.deleteByPrimaryKey(p.getId()); //删除产品数据
        //如果是活期产品，删除渠道产品数据
        if (p.getProductType().equals("0")) {
            chnProductDao.deleteByProductNo(p.getProductNo());
        } else {//如果是定期产品，删除定期产品回款计划表
            termPaymentScheduleMapper.deleteByProductNo(p.getProductNo());
        }

        //重新插入新数据
        productVo.setCreateTime(p.getCreateTime());//创建时间不变
        productVo.setGmtModified(new Date());//修改时间更新
        saveProduct(productVo);


        return SimpleWebResult.ok();
    }


    /**
     * 通过文件保存产品信息
     * 如果保存的是活期产品,要把活期产品信息同步到“渠道产品表”
     * 如果保存的定期产品,就要把定期产品信息同步到“定期产品回款计划表”
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult saveProduct(@RequestBody ProductVo productVo) {


        //保存前检查数据库中没有重复数据
        ProductVo p = productDao.selectAllInfoByProductNo(productVo.getProductNo());
        if (p != null) {
            SimpleWebResult error = SimpleWebResult.error("系统已经有重复产品:" + productVo.getProductNo());
            return error;
        }

        Product product = new Product();
        BeanUtils.copyProperties(productVo, product);


        if (product.getProductType().equals("1")) {
            List<TermPaymentSchedule> schedule = termPaymentService.createSchedule(product);
            termPaymentService.saveSchedule(schedule);

            //设置产品的期数信息
            Integer totalPeriods = schedule.get(0).getProductTotalPeriods();
            product.setProductTotalPeriods(totalPeriods);
            product.setStartPeriods(1);
            product.setEndPeriods(totalPeriods);
        }
        product.setNominalIncomeRate(product.getIncomeRate());
        product.setNominalPercentIncomeRate(product.getIncomeDifference());
        productService.saveProducts(product);

        return SimpleWebResult.ok();
    }


    /**
     * 条件查询产品列表
     */
    @RequestMapping(value = "/queryProducts", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult queryProducts(@RequestBody ProductQueryParam queryParam) {

        String productNo = queryParam.getProductNo();
        if (productNo == null || productNo.equals("")) {
            queryParam.setProductNo(null);
        }

        List<ProductVo> products = productService.queryProducts(queryParam);

        //把交易所代码转换为交易所名称
        for (ProductVo product : products) {
            String exchangeNo = product.getExchangeNo();
            ExchangeInfo map = exchangeInfoDao.queryExchangeInfo(exchangeNo);
            product.setExchangeNo(map.getExchangeCode());
        }


        SimpleWebResult result = SimpleWebResult.ok();
        result.setData(products);
        return result;
    }

    /**
     * 根据产品代码查询产品信息
     */
    @RequestMapping(value = "/queryProductInfo", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult queryProductInfo(@RequestParam("productNo") String productNo) {

        ProductVo p = productDao.selectAllInfoByProductNo(productNo);
        SimpleWebResult result = SimpleWebResult.ok();
        result.setData(p);
        return result;
    }


    @RequestMapping(value = "/addChildProduct", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult addChildProduct(@RequestBody ChildProductVo childProductVo) {


        productDividedService.addChild(childProductVo);

        SimpleWebResult result = SimpleWebResult.ok();

        return result;
    }


    @RequestMapping(value = "/queryChildProducts", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult queryChildProducts(String productNo, String exchangeNo) {

        List<ChildProductVo> productVos = productDividedService.queryChildProducts(productNo, exchangeNo);


        for (ChildProductVo productVo : productVos) {
            //招银交易所的新生成的子产品代码需要上传到交易所确认
            String increase = productVo.getIncrease();
            if (Integer.valueOf(increase) > 0 && productVo.getExchangeNo().equals(ExchangeType.Cmbfae.getExchangeNo())) {
                productVo.setUpload(true);
            }
        }

        SimpleWebResult result = SimpleWebResult.ok();
        result.setData(productVos);

        return result;
    }


    @RequestMapping(value = "/queryTermPaymentSchedule", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult queryTermPaymentSchedule(@RequestParam("productNo") String productNo, String startDate, String endDate) {

        List<TermPaymentScheduleVo> paymentScheduleVos = termPaymentScheduleMapper.selectByParam(startDate, endDate, productNo);

        SimpleWebResult result = SimpleWebResult.ok();
        result.setData(paymentScheduleVos);

        return result;
    }


    /**
     * 导入招银交易所的产品信息
     */
    @RequestMapping(value = "/importCmbfaeProduct", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult importCmbfaeProduct() throws Exception {

        List<com.jinhui.scheduler.domain.cmbfae.model.Product> products = cmbfaeProductService.importProductInfo();

        if (products.size() == 0) {
            SimpleWebResult result = SimpleWebResult.error("没有可导入的产品文件");
            return result;
        }

        SimpleWebResult result = SimpleWebResult.ok();

        result.setData(products);
        return result;
    }

    /**
     * 招银交易所子产品回传
     */
    @RequestMapping(value = "/uploadCmbfaeChildProduct", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult uploadCmbfaeChildProduct(@RequestParam("productNo") String productNo) throws Exception {

        cmbfaeProductDividedService.exportChildProductFile(productNo);
        SimpleWebResult result = SimpleWebResult.ok();


        return result;
    }


    /**
     * 生成产品要素文件file
     */

    @RequestMapping(value = "/issueProduct", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult issueProduct(@RequestParam("productNo") String productNo, @RequestParam("chnProductRate") String chnProductRate, @RequestParam("chnCode") String chnCode) throws Exception {

        //查询产品信息
        Product product = productDao.selectInfoByProductNo(productNo);
        //根据实际收益率计算实际的万元收益
        BigDecimal incomeRate = new BigDecimal(chnProductRate);
        BigDecimal realPercentIncome = ProductUtils.calProfit(incomeRate);
        //计算收益率差值(名义上的万元收益减去实际的万元收益)
        BigDecimal nominalPercentIncome = product.getNominalPercentIncomeRate();
        product.setIncomeDifference(nominalPercentIncome.subtract(realPercentIncome));
        //更新产品表的实际收益率和实际万元收益
        product.setGmtModified(new Date());
        product.setPercentIncomeRate(realPercentIncome);
        product.setIncomeRate(incomeRate);
        productDao.updateIncomeByNo(product);

        //如果保存的是活期产品并且如果渠道产品表中没有，就新插入渠道产品表
        if (product.getProductType().equals("0")) {
            chnProductService.createChnProductInfo(product, chnCode);
        }

        //生成产品要素表给渠道
        productService.issueProduct(chnCode, product);

        SimpleWebResult result = SimpleWebResult.ok();


        return result;
    }


}
