package com.jinhui.scheduler.domain.imiqian.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Integer id;

    private String productName;

    private String productNo;

    private String platProductNo;

    private String productIssuer;

    private String exchangeNo;

    private String productType;

    private Integer productExpdate;

    private Integer expdateUnit;

    private String calIncomeWay;

    private String calRateWay;

    private Date setupDate;

    private Date rateDate;

    private Date termDate;

    private Date casheDate;

    private BigDecimal incomeRate;

    private BigDecimal percentIncomeRate;

    private Date ipoStartDate;

    private Date ipoEndDate;

    private BigDecimal startPeriods;

    private BigDecimal endPeriods;

    private BigDecimal totalLimit;

    private BigDecimal totalVol;

    private BigDecimal productVol;

    private BigDecimal subsStartAmount;

    private BigDecimal subsRange;

    private String riskAssess;

    private String riskLevel;

    private int productTotalPeriods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getPlatProductNo() {
        return platProductNo;
    }

    public void setPlatProductNo(String platProductNo) {
        this.platProductNo = platProductNo == null ? null : platProductNo.trim();
    }

    public String getProductIssuer() {
        return productIssuer;
    }

    public void setProductIssuer(String productIssuer) {
        this.productIssuer = productIssuer == null ? null : productIssuer.trim();
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo == null ? null : exchangeNo.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getProductExpdate() {
        return productExpdate;
    }

    public void setProductExpdate(Integer productExpdate) {
        this.productExpdate = productExpdate;
    }

    public Integer getExpdateUnit() {
        return expdateUnit;
    }

    public void setExpdateUnit(Integer expdateUnit) {
        this.expdateUnit = expdateUnit;
    }

    public String getCalIncomeWay() {
        return calIncomeWay;
    }

    public void setCalIncomeWay(String calIncomeWay) {
        this.calIncomeWay = calIncomeWay == null ? null : calIncomeWay.trim();
    }

    public String getCalRateWay() {
        return calRateWay;
    }

    public void setCalRateWay(String calRateWay) {
        this.calRateWay = calRateWay == null ? null : calRateWay.trim();
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public Date getCasheDate() {
        return casheDate;
    }

    public void setCasheDate(Date casheDate) {
        this.casheDate = casheDate;
    }

    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(BigDecimal incomeRate) {
        this.incomeRate = incomeRate;
    }

    public BigDecimal getPercentIncomeRate() {
        return percentIncomeRate;
    }

    public void setPercentIncomeRate(BigDecimal percentIncomeRate) {
        this.percentIncomeRate = percentIncomeRate;
    }

    public Date getIpoStartDate() {
        return ipoStartDate;
    }

    public void setIpoStartDate(Date ipoStartDate) {
        this.ipoStartDate = ipoStartDate;
    }

    public Date getIpoEndDate() {
        return ipoEndDate;
    }

    public void setIpoEndDate(Date ipoEndDate) {
        this.ipoEndDate = ipoEndDate;
    }

    public BigDecimal getStartPeriods() {
        return startPeriods;
    }

    public void setStartPeriods(BigDecimal startPeriods) {
        this.startPeriods = startPeriods;
    }

    public BigDecimal getEndPeriods() {
        return endPeriods;
    }

    public void setEndPeriods(BigDecimal endPeriods) {
        this.endPeriods = endPeriods;
    }

    public BigDecimal getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }

    public BigDecimal getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(BigDecimal totalVol) {
        this.totalVol = totalVol;
    }

    public BigDecimal getProductVol() {
        return productVol;
    }

    public void setProductVol(BigDecimal productVol) {
        this.productVol = productVol;
    }

    public BigDecimal getSubsStartAmount() {
        return subsStartAmount;
    }

    public void setSubsStartAmount(BigDecimal subsStartAmount) {
        this.subsStartAmount = subsStartAmount;
    }

    public BigDecimal getSubsRange() {
        return subsRange;
    }

    public void setSubsRange(BigDecimal subsRange) {
        this.subsRange = subsRange;
    }

    public String getRiskAssess() {
        return riskAssess;
    }

    public void setRiskAssess(String riskAssess) {
        this.riskAssess = riskAssess == null ? null : riskAssess.trim();
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    public int getProductTotalPeriods() {
        return productTotalPeriods;
    }

    public void setProductTotalPeriods(int productTotalPeriods) {
        this.productTotalPeriods = productTotalPeriods;
    }
}