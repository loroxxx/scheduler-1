package com.jinhui.scheduler.domain.imiqian.domain;


public class ChnProduct {
    private Integer id;

    private String chnCode;

    private String productName;

    private String productNo;

    private String platProductNo;

    private Double totalLimit;

    private Double assignLimit;

    private Double totalVol;

    private Double assignVol;

    private Double limitControl;

    private Double incomeRate;

    private Double percentIncomeRate;

    private Double normalPercentIncomeRate;

    private String preferDate;

    private Double productVol;

    private Double subsToplimit;

    private Double redeemToplimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getPlatProductNo() {
        return platProductNo;
    }

    public void setPlatProductNo(String platProductNo) {
        this.platProductNo = platProductNo;
    }

    public Double getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Double totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Double getAssignLimit() {
        return assignLimit;
    }

    public void setAssignLimit(Double assignLimit) {
        this.assignLimit = assignLimit;
    }

    public Double getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(Double totalVol) {
        this.totalVol = totalVol;
    }

    public Double getAssignVol() {
        return assignVol;
    }

    public void setAssignVol(Double assignVol) {
        this.assignVol = assignVol;
    }

    public Double getLimitControl() {
        return limitControl;
    }

    public void setLimitControl(Double limitControl) {
        this.limitControl = limitControl;
    }

    public Double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(Double incomeRate) {
        this.incomeRate = incomeRate;
    }

    public Double getPercentIncomeRate() {
        return percentIncomeRate;
    }

    public void setPercentIncomeRate(Double percentIncomeRate) {
        this.percentIncomeRate = percentIncomeRate;
    }

    public String getPreferDate() {
        return preferDate;
    }

    public void setPreferDate(String preferDate) {
        this.preferDate = preferDate;
    }

    public Double getProductVol() {
        return productVol;
    }

    public void setProductVol(Double productVol) {
        this.productVol = productVol;
    }

    public Double getSubsToplimit() {
        return subsToplimit;
    }

    public void setSubsToplimit(Double subsToplimit) {
        this.subsToplimit = subsToplimit;
    }

    public Double getRedeemToplimit() {
        return redeemToplimit;
    }

    public void setRedeemToplimit(Double redeemToplimit) {
        this.redeemToplimit = redeemToplimit;
    }

    public Double getNormalPercentIncomeRate() {
        return normalPercentIncomeRate;
    }

    public void setNormalPercentIncomeRate(Double normalPercentIncomeRate) {
        this.normalPercentIncomeRate = normalPercentIncomeRate;
    }
}