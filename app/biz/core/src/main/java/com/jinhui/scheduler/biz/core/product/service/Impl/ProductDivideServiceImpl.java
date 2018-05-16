package com.jinhui.scheduler.biz.core.product.service.Impl;

import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.biz.core.product.service.ProductDividedService;
import com.jinhui.scheduler.data.core.mapper.core.ProductDao;
import com.jinhui.scheduler.data.core.mapper.core.ScheduleProductDividedMapper;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.core.ChildProductVo;
import com.jinhui.scheduler.domain.core.Product;
import com.jinhui.scheduler.domain.core.ProductDivided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/25 0025.
 */
@Service
public class ProductDivideServiceImpl implements ProductDividedService {

    @Autowired
    private ScheduleProductDividedMapper productDividedMapper;

    @Autowired
    private ProductDao productDao;

    /**
     * 生成子产品代码，格式为6位，前两位是字母，后四位数数字，前补零，例子：GZ0001
     */
    @Override
    public void addChild(ChildProductVo childProductVo) {

        String startNo = childProductVo.getStartNo();//起始代码

        int addNum = Integer.valueOf(childProductVo.getAddNum()); //添加的组数

        String code;
        int startNum;
        try {
            code = startNo.substring(0, 2);
            startNum = Integer.valueOf(startNo.substring(2));
        } catch (RuntimeException e) {
            throw new BizException("子产品起始代码的格式不正确,必须是两位字母+四位数字");
        }

        ArrayList<ProductDivided> list = new ArrayList();

        for (int i = 1; i <= addNum; i++) {
            //构造子产品代码
            String pattern = code + "%04d";
            String format = String.format(pattern, startNum);
            startNum++;
            ProductDivided productDivided = new ProductDivided();
            productDivided.setChildProductNo(format);
            productDivided.setProductNo(childProductVo.getProductNo());
            productDivided.setProductName(childProductVo.getProductName());
            productDivided.setCount(0);
            productDivided.setGmtCreate(new Date());
            productDivided.setGmtModified(new Date());

            //如果子产品需要上传，状态为0，否则为1
            if (childProductVo.getExchangeNo().equals(ExchangeType.Cmbfae.getExchangeNo())) {
                productDivided.setState("0");
            } else {
                productDivided.setState("1");
            }

            list.add(productDivided);
        }

        productDividedMapper.insertList(list);

    }



    @Override
    public List<ChildProductVo> queryChildProducts(String productNo, String exchangeNo) {


        //查询产品信息
        List<Product> products = productDao.selectCurrent(productNo, exchangeNo);

        ArrayList list = new ArrayList();

        for (Product productInfo : products) {

            //查询已经被交易所确认的子产品的数量
            int confirmCount=productDividedMapper.selectCountByState(productInfo.getProductNo(),"1");

            ChildProductVo childProductVo = new ChildProductVo();

            childProductVo.setProductNo(productInfo.getProductNo());
            childProductVo.setProductName(productInfo.getProductName());
            childProductVo.setPlatProductNo(productInfo.getPlatProductNo());
            childProductVo.setExchangeNo(productInfo.getExchangeNo());
            childProductVo.setIncrease("0");
            int totalCount = productDividedMapper.selectCount(productInfo.getProductNo());
            //如果子产品的的总数量大于0,跟随上一个分组，并设置起始代码在原基础上加一
            if (totalCount > 0) {
                childProductVo.setFlow("0");
                //找到最大的一个子产品
                ProductDivided maxRecord = productDividedMapper.selectMaxRecord(productInfo.getProductNo());
                String startNo = maxRecord.getChildProductNo();
                String code = startNo.substring(0, 2);
                int startNum = Integer.valueOf(startNo.substring(2));
                String pattern = code + "%04d";
                String format = String.format(pattern, (startNum + 1));
                childProductVo.setStartNo(format);

                //查询未确认的子产品数量
                int num = productDividedMapper.selectCountByState(productInfo.getProductNo(),"0");
                childProductVo.setIncrease(String.valueOf(num));

            }


            //查询子产品当前使用的代码
            ProductDivided currentProduct = productDividedMapper.selectCurrent(productInfo.getProductNo());
            if (currentProduct != null) {
                String childProductNo = currentProduct.getChildProductNo();
                int userQuantity = Integer.valueOf(childProductNo.substring(3, 6)); //当前组数
                childProductVo.setChildProductNo(currentProduct.getChildProductNo());
                childProductVo.setUseQuantity(String.valueOf(userQuantity));
                childProductVo.setNonUseQuantity(String.valueOf(confirmCount - userQuantity));
                childProductVo.setUseSummary(userQuantity + "/" + confirmCount);
                childProductVo.setCreateTime(currentProduct.getGmtCreate());

            } else {
                childProductVo.setUseQuantity(String.valueOf(0));
                childProductVo.setNonUseQuantity(String.valueOf(confirmCount));
                childProductVo.setUseSummary(0 + "/" + confirmCount);
            }

            list.add(childProductVo);
        }

        return list;


    }


}
