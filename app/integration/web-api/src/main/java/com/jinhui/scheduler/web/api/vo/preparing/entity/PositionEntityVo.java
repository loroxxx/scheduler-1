package com.jinhui.scheduler.web.api.vo.preparing.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/5/31.
 */
public class PositionEntityVo {

    @ApiModelProperty(value="产品代码")
    private String productNo;
    @ApiModelProperty(value="产品名称")
    private String productName;
    @ApiModelProperty(value="投资人ID")
    private String investorId;
    @ApiModelProperty(value="投资人姓名")
    private String investorName;
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
        return "PositionEntityVo{" +
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
