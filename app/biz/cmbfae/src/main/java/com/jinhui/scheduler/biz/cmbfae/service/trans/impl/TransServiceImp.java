package com.jinhui.scheduler.biz.cmbfae.service.trans.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.jinhui.scheduler.biz.cmbfae.service.trans.TransService;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.domain.cmbfae.TransStatistics;
import com.jinhui.scheduler.domain.common.ExchangeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeConst;
import com.jinhui.scheduler.data.core.mapper.cmbfae.TransDao;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.cmbfae.template.TransSuContent;

@Service("TransService")
public class TransServiceImp implements TransService {
    private Logger log = LoggerFactory.getLogger(TransServiceImp.class);

    @Autowired
    private TransDao transDao;


    @Autowired
    private BatchStateService batchStateService;



    @Override
    public List<TransSuContent> querySummarize() {


        ArrayList<TransSuContent> list = new ArrayList();

        String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();

        BatchState current = batchStateService.getBatchStateCurrent();

        List<TransStatistics> statisticses = transDao.queryTransStatistics(exchangeNo, current.getBatchCode());

        //找出不同的产品子代码
        List<String> childProductNos = statisticses.stream().map(t -> t.getChildProductNo()).distinct().collect(Collectors.toList());


        //按交易类型划分
        //申购交易
        List<TransStatistics> purchase = statisticses.stream().filter(t -> t.getTranType().equals(CmbfaeConst.BUSINESS_CODE_PURCHASE_APPLY)).collect(Collectors.toList());
        //赎回交易
        List<TransStatistics> redeem = statisticses.stream().filter(t -> t.getTranType().equals(CmbfaeConst.BUSINESS_CODE_REDEEM_APPLY)).collect(Collectors.toList());


        //按子产品代码划分
        for (String childProductNo : childProductNos) {

            //得到该子产品的申购交易
            Optional<TransStatistics> first = purchase.stream().filter(t -> t.getChildProductNo().equals(childProductNo)).findFirst();
            TransStatistics purchaseStatistics = first.orElse(null);


            //得到该子产品的赎回交易
            Optional<TransStatistics> first1 = redeem.stream().filter(t -> t.getChildProductNo().equals(childProductNo)).findFirst();
            TransStatistics redeemStatistics1 = first1.orElse(null);


            if (purchaseStatistics == null && redeemStatistics1 == null) {
                throw new BizException("当前批次没有交易记录，不生成交易文件");
            }


            TransSuContent suContent = new TransSuContent();
            if (purchaseStatistics != null) {
                suContent.setProductCode(purchaseStatistics.getChildProductNo());
                suContent.setProductName(purchaseStatistics.getProductName());
                suContent.setPurchaseAmount(new BigDecimal(purchaseStatistics.getTotalAmount()));
                suContent.setPurchaseRecord(Long.valueOf(purchaseStatistics.getTransCount()));

            } else {
                suContent.setPurchaseAmount(new BigDecimal("0"));
                suContent.setPurchaseRecord(0);
            }

            if (redeemStatistics1 != null) {
                suContent.setProductCode(redeemStatistics1.getChildProductNo());
                suContent.setProductName(redeemStatistics1.getProductName());
                suContent.setPaybackAmount(new BigDecimal(redeemStatistics1.getTotalAmount()));
                suContent.setPaybackRecord(Long.valueOf(redeemStatistics1.getTransCount()));
            } else {
                suContent.setPaybackAmount(new BigDecimal("0"));
                suContent.setPaybackRecord(0);
            }

            suContent.setSumRecord((suContent.getPaybackRecord() + suContent.getPurchaseRecord()));
            list.add(suContent);
        }


        return list;
    }

    @Override
    public boolean hasTrans() {

        BatchState current = batchStateService.getBatchStateCurrent();
        String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();

        int count = transDao.queryTransCount(current.getBatchCode(),exchangeNo);

        if (count==0){
            return false;
        }

        return true;
    }



}
