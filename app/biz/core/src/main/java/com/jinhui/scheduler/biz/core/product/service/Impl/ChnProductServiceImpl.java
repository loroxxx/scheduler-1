package com.jinhui.scheduler.biz.core.product.service.Impl;

import com.jinhui.scheduler.biz.core.product.service.ChnProductService;
import com.jinhui.scheduler.biz.core.util.ProductUtils;
import com.jinhui.scheduler.data.core.mapper.core.ChnProductDao;
import com.jinhui.scheduler.data.core.mapper.core.ChnProductHistoryDao;
import com.jinhui.scheduler.data.core.mapper.core.ProductDao;
import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;
import com.jinhui.scheduler.domain.core.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("ChnProductService")
public class ChnProductServiceImpl implements ChnProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ChnProductDao chnProductDao;


    @Autowired
    private ChnProductHistoryDao chnProductHistoryDao;


    @Override
    public void createChnProductInfo(Product product, String chnCode) {

        ChnProduct product1 = chnProductDao.selectByProductNo(product.getProductNo());
        if (product1 != null) {
            return;
        }

        // 初始化渠道产品信息
        ChnProduct chnProduct = new ChnProduct();
        chnProduct.setProductName(product.getProductName());
        chnProduct.setProductNo(product.getProductNo());
        chnProduct.setIncomeRate(product.getIncomeRate());

        BigDecimal percentIncomeRate = product.getPercentIncomeRate();
        if (percentIncomeRate == null) {
            product.setPercentIncomeRate(ProductUtils.calProfit(product.getIncomeRate()));
        }

        chnProduct.setPercentIncomeRate(product.getPercentIncomeRate());
        chnProduct.setSubsToplimit(new BigDecimal(0));
        chnProduct.setPositionLimit(new BigDecimal(0));
        chnProduct.setRedeemToplimit(new BigDecimal(0));
        chnProduct.setProductRedeemToplimit(new BigDecimal(0));
        chnProduct.setTotalLimit(product.getTotalLimit());
        chnProduct.setAssignLimit(new BigDecimal(0));
        chnProduct.setProductSubsToplimit(new BigDecimal(0));
        chnProduct.setProductRedeemToplimit(new BigDecimal(0));
        chnProduct.setChnCode(chnCode);

        chnProduct.setNominalIncomeRate(product.getNominalIncomeRate());//"名义上的收益率"
        chnProduct.setNominalPercentIncomeRate(product.getNominalPercentIncomeRate());//名义上的万元收益
        //名义上的万元收益减去实际的万元收益
        BigDecimal incomeDifference = product.getNominalIncomeRate().subtract(product.getIncomeRate());

        System.out.println(incomeDifference);
        product.setIncomeDifference(incomeDifference);

        chnProductDao.saveChnProduct(chnProduct);
    }

    @Override
    public List<ChnProduct> queryChnProductInfo(String chnCode) {

        return chnProductDao.queryChnProduct(chnCode);
    }

    @Override
    public int updateChnProduct(ChnProduct cp, Date perferDate) {

        //收益率百分比
        BigDecimal incomeRate = cp.getIncomeRate();
        BigDecimal divide = incomeRate.divide(new BigDecimal(100));
        cp.setIncomeRate(divide);
        //根据实际收益率计算实际的万元收益
        BigDecimal realIncome = ProductUtils.calProfit(cp.getIncomeRate());
        cp.setPercentIncomeRate(realIncome);

        //计算收益率差值(名义上的万元收益减去实际的万元收益)
        BigDecimal nominalIncome = cp.getNominalPercentIncomeRate();
        cp.setIncomeDifference(nominalIncome.subtract(realIncome));
        cp.setPreferDate(perferDate);


        //同时也更新产品的实际收益率
        Product product = new Product();
        product.setProductNo(cp.getProductNo());
        product.setIncomeRate(divide);
        product.setPercentIncomeRate(realIncome);
        product.setIncomeDifference(nominalIncome.subtract(realIncome));
        product.setGmtModified(new Date());
        productDao.updateIncomeByNo(product);


        return chnProductDao.updateChnProduct(cp);
    }

    @Override
    public void backupChnProductHistory(String chnCode) {

        List<ChnProduct> list = chnProductDao.queryChnProduct(chnCode);
        for (ChnProduct chnProduct : list) {

            //历史表中没有记录则插入，有则更新
            List historyList = chnProductHistoryDao.queryChnProductHistory(chnProduct.getProductNo(), chnProduct.getChnCode(), chnProduct.getPreferDate());
            if (historyList.size() == 0) {
                chnProductHistoryDao.insertChnProductHistory(chnProduct);
            } else {
                chnProductHistoryDao.updateChnProductHistory(chnProduct);
            }
        }

    }


}
