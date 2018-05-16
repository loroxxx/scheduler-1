package com.jinhui.scheduler.domain.imiqian.domain;

public class ProductHistoryRate {
    private Integer id;

    private String productNo;

    private String exchangeCode;

    private String productName;

    private String productType;

    private Double incomeRate;

    private Double percentIncome;

    private String createTime;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    @Override
    public String toString() {
        return "ProductHistoryRate{" +
                "id=" + id +
                ", productNo='" + productNo + '\'' +
                ", exchangeCode='" + exchangeCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", incomeRate=" + incomeRate +
                ", percentIncome=" + percentIncome +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}