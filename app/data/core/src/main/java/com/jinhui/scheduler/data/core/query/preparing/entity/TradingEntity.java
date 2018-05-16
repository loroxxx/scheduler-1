package com.jinhui.scheduler.data.core.query.preparing.entity;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TradingEntity {

    private String productNo;
    private String productName;
    private String investorId;
    private String investorName;
    //认购金额
    private BigDecimal subscribeAmount;
    //当日申购限额
    private BigDecimal subscribeLimit;
    private BigDecimal redeemAmount;
    //当日赎回限额
    private BigDecimal redeemLimit;
    //持仓金额
    private BigDecimal positionAmount;
    //持仓限额
    private BigDecimal positionLimit;
    //汇总日期
    private String batchDate;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public BigDecimal getSubscribeLimit() {
        return subscribeLimit;
    }

    public void setSubscribeLimit(BigDecimal subscribeLimit) {
        this.subscribeLimit = subscribeLimit;
    }

    public BigDecimal getRedeemLimit() {
        return redeemLimit;
    }

    public void setRedeemLimit(BigDecimal redeemLimit) {
        this.redeemLimit = redeemLimit;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    public BigDecimal getSubscribeAmount() {
        return subscribeAmount;
    }

    public void setSubscribeAmount(BigDecimal subscribeAmount) {
        this.subscribeAmount = subscribeAmount;
    }

    public BigDecimal getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(BigDecimal redeemAmount) {
        this.redeemAmount = redeemAmount;
    }

    public BigDecimal getPositionAmount() {
        return positionAmount;
    }

    public void setPositionAmount(BigDecimal positionAmount) {
        this.positionAmount = positionAmount;
    }

    public BigDecimal getPositionLimit() {
        return positionLimit;
    }

    public void setPositionLimit(BigDecimal positionLimit) {
        this.positionLimit = positionLimit;
    }
}
