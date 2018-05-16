package com.jinhui.scheduler.domain.imiqian.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TermPaymentSchedule {
    private Integer id;

    private String productName;

    private String productNo;

    private String platProductNo;

    private BigDecimal totalAmount;

    private Integer productTotalPeriods;

    private BigDecimal interestAmount;

    private Integer interestPeriod;

    private Integer interestDays;

    private BigDecimal perInterestAmount;

    private String interestState;

    private Date interestDate;

    private Date createTime;

    private Date gmtModified;

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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getProductTotalPeriods() {
        return productTotalPeriods;
    }

    public void setProductTotalPeriods(Integer productTotalPeriods) {
        this.productTotalPeriods = productTotalPeriods;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Integer getInterestPeriod() {
        return interestPeriod;
    }

    public void setInterestPeriod(Integer interestPeriod) {
        this.interestPeriod = interestPeriod;
    }

    public Integer getInterestDays() {
        return interestDays;
    }

    public void setInterestDays(Integer interestDays) {
        this.interestDays = interestDays;
    }

    public BigDecimal getPerInterestAmount() {
        return perInterestAmount;
    }

    public void setPerInterestAmount(BigDecimal perInterestAmount) {
        this.perInterestAmount = perInterestAmount;
    }

    public String getInterestState() {
        return interestState;
    }

    public void setInterestState(String interestState) {
        this.interestState = interestState == null ? null : interestState.trim();
    }

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}