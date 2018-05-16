package com.jinhui.scheduler.domain.imiqian.domain;

public class InvestorIncome {
    private Integer id;

    private String serialNumber;

    private String gatherDate;

    private String downloadDate;

    private int batchCode;

    private String investorId;

    private String chnId;

    private String chnCode;

    private String name;

    private String applyDate;

    private String confirmDate;

    private String productNo;

    private String childProductNo;

    private String productName;

    private Double totalPostionAmount;

    private Double incomeAmount;

    private String incomeType;

    private Double incomeRate;

    private Double percentIncome;

    private String incomeState;

    private String createTime;

    private String updateTime;

    private String beginDate;

    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getGatherDate() {
        return gatherDate;
    }

    public void setGatherDate(String gatherDate) {
        this.gatherDate = gatherDate;
    }

    public String getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(String downloadDate) {
        this.downloadDate = downloadDate;
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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
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

    public Double getTotalPostionAmount() {
        return totalPostionAmount;
    }

    public void setTotalPostionAmount(Double totalPostionAmount) {
        this.totalPostionAmount = totalPostionAmount;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public Double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public Double getPercentIncome() {
        return percentIncome;
    }

    public void setPercentIncome(Double percentIncome) {
        this.percentIncome = percentIncome;
    }

    public String getIncomeState() {
        return incomeState;
    }

    public void setIncomeState(String incomeState) {
        this.incomeState = incomeState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "InvestorIncome{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", gatherDate='" + gatherDate + '\'' +
                ", downloadDate='" + downloadDate + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", investorId='" + investorId + '\'' +
                ", chnId='" + chnId + '\'' +
                ", chnCode='" + chnCode + '\'' +
                ", name='" + name + '\'' +
                ", applyDate='" + applyDate + '\'' +
                ", confirmDate='" + confirmDate + '\'' +
                ", productNo='" + productNo + '\'' +
                ", childProductNo='" + childProductNo + '\'' +
                ", productName='" + productName + '\'' +
                ", totalPostionAmount=" + totalPostionAmount +
                ", incomeAmount=" + incomeAmount +
                ", incomeType='" + incomeType + '\'' +
                ", incomeRate=" + incomeRate +
                ", percentIncome=" + percentIncome +
                ", incomeState='" + incomeState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}