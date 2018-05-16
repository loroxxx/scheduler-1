package com.jinhui.scheduler.domain.imiqian.domain;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessGather {
    private Integer id;

    private int batchCode;

    private String gatherDate;

    private String platProductNo;

    private String productNo;

    private String productName;

    private String chnCode;

    private String transType;

    private int transNum;

    private Double transVol;

    private Double transAmount;

    private Double successVol;

    private Double failVol;

    private Double successAmount;

    private Double failAmount;

    private int successNum;

    private int failNum;

    private String beginDate;

    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    public String getGatherDate() {
        return gatherDate;
    }

    public void setGatherDate(String gatherDate) {
        this.gatherDate = gatherDate;
    }

    public String getPlatProductNo() {
        return platProductNo;
    }

    public void setPlatProductNo(String platProductNo) {
        this.platProductNo = platProductNo;
    }

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

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public int getTransNum() {
        return transNum;
    }

    public void setTransNum(int transNum) {
        this.transNum = transNum;
    }

    public Double getTransVol() {
        return transVol;
    }

    public void setTransVol(Double transVol) {
        this.transVol = transVol;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public Double getSuccessVol() {
        return successVol;
    }

    public void setSuccessVol(Double successVol) {
        this.successVol = successVol;
    }

    public Double getFailVol() {
        return failVol;
    }

    public void setFailVol(Double failVol) {
        this.failVol = failVol;
    }

    public Double getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(Double successAmount) {
        this.successAmount = successAmount;
    }

    public Double getFailAmount() {
        return failAmount;
    }

    public void setFailAmount(Double failAmount) {
        this.failAmount = failAmount;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
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
        return "BusinessGather{" +
                "id=" + id +
                ", batchCode='" + batchCode + '\'' +
                ", gatherDate='" + gatherDate + '\'' +
                ", platProductNo='" + platProductNo + '\'' +
                ", productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", chnCode='" + chnCode + '\'' +
                ", transType='" + transType + '\'' +
                ", transNum=" + transNum +
                ", transVol=" + transVol +
                ", transAmount=" + transAmount +
                ", successVol=" + successVol +
                ", failVol=" + failVol +
                ", successAmount=" + successAmount +
                ", failAmount=" + failAmount +
                ", successNum=" + successNum +
                ", failNum=" + failNum +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}