package com.jinhui.scheduler.domain.imiqian.domain;

import java.math.BigDecimal;

public class InvestorPosition {
    private Integer id;

    private String gatherDate;

    private int batchCode;

    private String investorId;

    private String chnId;

    private String chnCode;

    private String name;

    private String productNo;

    private String childProductNo;

    private String productName;

    private Double totalPostionVol;

    private Double productVol;

    private Double totalPostionAmount;

    private Double totalIncome;

    private Double totalRedemedAmount;

    private Double totalSubsAmount;

    private String rollingFlag;

    private Double rollingVol;

    private String beginDate;

    private String endDate;

    private String positionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGatherDate() {
        return gatherDate;
    }

    public void setGatherDate(String gatherDate) {
        this.gatherDate = gatherDate;
    }

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getChnId() {
        return chnId;
    }

    public void setChnId(String chnId) {
        this.chnId = chnId;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getChildProductNo() {
        return childProductNo;
    }

    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getTotalPostionVol() {
        return totalPostionVol;
    }

    public void setTotalPostionVol(Double totalPostionVol) {
        this.totalPostionVol = totalPostionVol;
    }

    public Double getProductVol() {
        return productVol;
    }

    public void setProductVol(Double productVol) {
        this.productVol = productVol;
    }

    public Double getTotalPostionAmount() {
        return totalPostionAmount;
    }

    public void setTotalPostionAmount(Double totalPostionAmount) {
        this.totalPostionAmount = totalPostionAmount;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalRedemedAmount() {
        return totalRedemedAmount;
    }

    public void setTotalRedemedAmount(Double totalRedemedAmount) {
        this.totalRedemedAmount = totalRedemedAmount;
    }

    public Double getTotalSubsAmount() {
        return totalSubsAmount;
    }

    public void setTotalSubsAmount(Double totalSubsAmount) {
        this.totalSubsAmount = totalSubsAmount;
    }

    public String getRollingFlag() {
        return rollingFlag;
    }

    public void setRollingFlag(String rollingFlag) {
        this.rollingFlag = rollingFlag;
    }

    public Double getRollingVol() {
        return rollingVol;
    }

    public void setRollingVol(Double rollingVol) {
        this.rollingVol = rollingVol;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    @Override
    public String toString() {
        return "InvestorPosition{" +
                "id=" + id +
                ", gatherDate='" + gatherDate + '\'' +
                ", batchCode=" + batchCode +
                ", investorId='" + investorId + '\'' +
                ", chnId='" + chnId + '\'' +
                ", chnCode='" + chnCode + '\'' +
                ", name='" + name + '\'' +
                ", productNo='" + productNo + '\'' +
                ", childProductNo='" + childProductNo + '\'' +
                ", productName='" + productName + '\'' +
                ", totalPostionVol=" + totalPostionVol +
                ", productVol=" + productVol +
                ", totalPostionAmount=" + totalPostionAmount +
                ", totalIncome=" + totalIncome +
                ", totalRedemedAmount=" + totalRedemedAmount +
                ", totalSubsAmount=" + totalSubsAmount +
                ", rollingFlag='" + rollingFlag + '\'' +
                ", rollingVol=" + rollingVol +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", positionType='" + positionType + '\'' +
                '}';
    }
}