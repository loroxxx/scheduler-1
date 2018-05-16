package com.jinhui.scheduler.domain.zlrt;

import java.math.BigDecimal;

public class ZlBalanceAccResult {
    private Integer id;

    private String instuId;

    private String fundDate;

    private String fundTime;

    private String liqDate;

    private String fundSeqId;

    private String orgFundDate;

    private String orgFundSeqId;

    private String busiType;

    private String userId;

    private String userNameText;

    private String certType;

    private BigDecimal transAmt;

    private String fundCode;

    private String fundType;

    private String pnrDate;

    private String pnrTime;

    private String pnrSeqId;

    private String balanceResult;

    private String chnCode;

    private String batchCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstuId() {
        return instuId;
    }

    public void setInstuId(String instuId) {
        this.instuId = instuId;
    }

    public String getFundDate() {
        return fundDate;
    }

    public void setFundDate(String fundDate) {
        this.fundDate = fundDate;
    }

    public String getFundTime() {
        return fundTime;
    }

    public void setFundTime(String fundTime) {
        this.fundTime = fundTime;
    }

    public String getLiqDate() {
        return liqDate;
    }

    public void setLiqDate(String liqDate) {
        this.liqDate = liqDate;
    }

    public String getFundSeqId() {
        return fundSeqId;
    }

    public void setFundSeqId(String fundSeqId) {
        this.fundSeqId = fundSeqId;
    }

    public String getOrgFundDate() {
        return orgFundDate;
    }

    public void setOrgFundDate(String orgFundDate) {
        this.orgFundDate = orgFundDate;
    }

    public String getOrgFundSeqId() {
        return orgFundSeqId;
    }

    public void setOrgFundSeqId(String orgFundSeqId) {
        this.orgFundSeqId = orgFundSeqId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(String userNameText) {
        this.userNameText = userNameText;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getPnrDate() {
        return pnrDate;
    }

    public void setPnrDate(String pnrDate) {
        this.pnrDate = pnrDate;
    }

    public String getPnrTime() {
        return pnrTime;
    }

    public void setPnrTime(String pnrTime) {
        this.pnrTime = pnrTime;
    }

    public String getPnrSeqId() {
        return pnrSeqId;
    }

    public void setPnrSeqId(String pnrSeqId) {
        this.pnrSeqId = pnrSeqId;
    }

    public String getBalanceResult() {
        return balanceResult;
    }

    public void setBalanceResult(String balanceResult) {
        this.balanceResult = balanceResult;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Override
    public String toString() {
        return "ZlBalanceAccResult{" +
                "id=" + id +
                ", instuId='" + instuId + '\'' +
                ", fundDate='" + fundDate + '\'' +
                ", fundTime='" + fundTime + '\'' +
                ", liqDate='" + liqDate + '\'' +
                ", fundSeqId='" + fundSeqId + '\'' +
                ", orgFundDate='" + orgFundDate + '\'' +
                ", orgFundSeqId='" + orgFundSeqId + '\'' +
                ", busiType='" + busiType + '\'' +
                ", userId='" + userId + '\'' +
                ", userNameText='" + userNameText + '\'' +
                ", certType='" + certType + '\'' +
                ", transAmt=" + transAmt +
                ", fundCode='" + fundCode + '\'' +
                ", fundType='" + fundType + '\'' +
                ", pnrDate='" + pnrDate + '\'' +
                ", pnrTime='" + pnrTime + '\'' +
                ", pnrSeqId='" + pnrSeqId + '\'' +
                ", balanceResult='" + balanceResult + '\'' +
                '}';
    }
}