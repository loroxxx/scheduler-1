package com.jinhui.scheduler.web.api.vo.preparing.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/5/31.
 */
public class TradingEntityVo {
    /**
     * 产品代码	产品名称	投资人ID	投资人姓名	申购金额	当日申购额度上限	汇总日期
     */
    @ApiModelProperty(value="产品代码")
    private String productNo;
    @ApiModelProperty(value="产品名称")
    private String productName;
    @ApiModelProperty(value="投资人ID")
    private String investorId;
    @ApiModelProperty(value="投资人姓名")
    private String investorName;
    @ApiModelProperty(value="购买金额")
    private BigDecimal subscribeAmount;
    @ApiModelProperty(value="赎回金额")
    private BigDecimal redeemAmount;
    @ApiModelProperty(value="购买限额")
    private BigDecimal subscribeLimit;
    @ApiModelProperty(value="赎回限额")
    private BigDecimal redeemLimit;
    @ApiModelProperty(value="持仓金额")
    private BigDecimal positionAmount;
    @ApiModelProperty(value="持仓限额")
    private BigDecimal positionLimit;
    @ApiModelProperty(value="批次日期")
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

    @Override
    public String toString() {
        return "TradingEntityVo{" +
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", investorId='" + investorId + '\'' +
                ", investorName='" + investorName + '\'' +
                ", subscribeAmount=" + subscribeAmount +
                ", redeemAmount=" + redeemAmount +
                ", subscribeLimit=" + subscribeLimit +
                ", redeemLimit=" + redeemLimit +
                ", positionAmount=" + positionAmount +
                ", positionLimit=" + positionLimit +
                ", batchDate='" + batchDate + '\'' +
                '}';
    }
}
