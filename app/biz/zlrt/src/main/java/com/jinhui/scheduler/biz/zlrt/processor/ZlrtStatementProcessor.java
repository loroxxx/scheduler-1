package com.jinhui.scheduler.biz.zlrt.processor;



import com.jinhui.scheduler.biz.zlrt.dto.ZlrtStatementLineItem;
import com.jinhui.scheduler.biz.zlrt.utils.ConvertorUtils;
import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

/**
 * 证联对账单数据构建对象
 *
 * @autor wsc
 * @create 2017-11-15 14:33
 **/
public class ZlrtStatementProcessor implements ItemProcessor<ZlrtStatementLineItem, ZlBalanceAccBill> {
    private final static Logger logger = LoggerFactory.getLogger(ZlrtStatementProcessor.class);

    @Override
    public ZlBalanceAccBill process(ZlrtStatementLineItem zlrtStatementLineItem) throws Exception {
        ZlBalanceAccBill zslb = new ZlBalanceAccBill();
        zslb.setInstuId(zlrtStatementLineItem.getInstuId().substring(zlrtStatementLineItem.getInstuId().indexOf("=")+1,zlrtStatementLineItem.getInstuId().length()));
        zslb.setFundDate(zlrtStatementLineItem.getFundDate().substring(zlrtStatementLineItem.getFundDate().indexOf("=")+1,zlrtStatementLineItem.getFundDate().length()));
        zslb.setFundTime(zlrtStatementLineItem.getFundTime().substring(zlrtStatementLineItem.getFundTime().indexOf("=")+1,zlrtStatementLineItem.getFundTime().length()));
        zslb.setLiqDate(zlrtStatementLineItem.getLiqDate().substring(zlrtStatementLineItem.getLiqDate().indexOf("=")+1,zlrtStatementLineItem.getLiqDate().length()));
        zslb.setFundSeqId(zlrtStatementLineItem.getFundSeqId().substring(zlrtStatementLineItem.getFundSeqId().indexOf("=")+1,zlrtStatementLineItem.getFundSeqId().length()));
        zslb.setOrgFundDate(zlrtStatementLineItem.getOrgFundDate().substring(zlrtStatementLineItem.getOrgFundDate().indexOf("=")+1,zlrtStatementLineItem.getOrgFundDate().length()));
        zslb.setOrgFundSeqId(zlrtStatementLineItem.getOrgFundSeqId().substring(zlrtStatementLineItem.getOrgFundSeqId().indexOf("=")+1,zlrtStatementLineItem.getOrgFundSeqId().length()));
        zslb.setBusiType(zlrtStatementLineItem.getBusiType().substring(zlrtStatementLineItem.getBusiType().indexOf("=")+1,zlrtStatementLineItem.getBusiType().length()));
        zslb.setUserId(zlrtStatementLineItem.getUserId().substring(zlrtStatementLineItem.getUserId().indexOf("=")+1,zlrtStatementLineItem.getUserId().length()));
        zslb.setUserNameText(zlrtStatementLineItem.getUserNameText().substring(zlrtStatementLineItem.getUserNameText().indexOf("=")+1,zlrtStatementLineItem.getUserNameText().length()));
        zslb.setCertId(zlrtStatementLineItem.getCertId().substring(zlrtStatementLineItem.getCertId().indexOf("=")+1,zlrtStatementLineItem.getCertId().length()));
        zslb.setCertType(zlrtStatementLineItem.getCertType().substring(zlrtStatementLineItem.getCertType().indexOf("=")+1,zlrtStatementLineItem.getCertType().length()));
        zslb.setTransAmt(ConvertorUtils.convertToStrDivideOneHundred(
                zlrtStatementLineItem.getTransAmt().substring(zlrtStatementLineItem.getTransAmt().indexOf("=")+1,zlrtStatementLineItem.getTransAmt().length())));
        zslb.setFundCode(zlrtStatementLineItem.getFundCode().substring(zlrtStatementLineItem.getFundCode().indexOf("=")+1,zlrtStatementLineItem.getFundCode().length()));
        zslb.setFundType(zlrtStatementLineItem.getFundType().substring(zlrtStatementLineItem.getFundType().indexOf("=")+1,zlrtStatementLineItem.getFundType().length()));
        zslb.setPnrDate(zlrtStatementLineItem.getPnrDate().substring(zlrtStatementLineItem.getPnrDate().indexOf("=")+1,zlrtStatementLineItem.getPnrDate().length()));
        zslb.setPnrSeqId(zlrtStatementLineItem.getPnrSeqId().substring(zlrtStatementLineItem.getPnrSeqId().indexOf("=")+1,zlrtStatementLineItem.getPnrSeqId().length()));
        zslb.setPnrTime(zlrtStatementLineItem.getPnrTime().substring(zlrtStatementLineItem.getPnrTime().indexOf("=")+1,zlrtStatementLineItem.getPnrTime().length()));

        return zslb;
    }


}
