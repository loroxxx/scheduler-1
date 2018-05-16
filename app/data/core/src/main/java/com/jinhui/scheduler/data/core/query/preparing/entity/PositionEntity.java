package com.jinhui.scheduler.data.core.query.preparing.entity;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/2.
 */
public class PositionEntity {
    private String productNo;
    private String productName;
    private String investorId;
    private String investorName;
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

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public String toString() {
        return "PositionEntity{" +
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", investorId='" + investorId + '\'' +
                ", investorName='" + investorName + '\'' +
                ", positionAmount=" + positionAmount +
                ", positionLimit=" + positionLimit +
                ", batchDate='" + batchDate + '\'' +
                '}';
    }
}
