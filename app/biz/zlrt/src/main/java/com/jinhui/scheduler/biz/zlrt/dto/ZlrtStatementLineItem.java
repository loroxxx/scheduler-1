package com.jinhui.scheduler.biz.zlrt.dto;

/**
 * 证联融通对账单读取的映射对象
 *
 *  wsc
 *  2017-11-15 14:20
 **/
public class ZlrtStatementLineItem {
    //证联支付分配给商户的机构代码
    private String instuId;
    //商户的系统日期，YYYYMMDD
    private String fundDate;
    //商户的时间戳，HHMMSS
    private String fundTime;
    //交易结算日期，YYYYMMDD；不小于交易日期
    private String liqDate;
    //商户系统流水号。需要保证一个交易日中流水号的唯一性
    private String fundSeqId;
    //撤单原始基金申购交易日期，YYYYMMDD
    private String orgFundDate;
    //撤单原始基金申购交易流水号
    private String orgFundSeqId;
    //业务类型 认购 0001、申购0002、定投0003、撤单0007,协议申购 1002、现金易支付 1001,1003,1004,1005
    private String busiType;
    //用户在证联支付平台里的客户号
    private String userId;
    //客户的姓名
    private String userNameText;
    //证件类型
    private String certType;
    //证件号码
    private String certId;
    //基金交易金额
    private String transAmt;
    //基金申购、赎回、分红交易中，基金公司发行的基金代码
    private String fundCode;
    //基金类型
    private String fundType;
    //证联支付发起交易的系统日期
    private String pnrDate;
    //证联支付发给基金的时间戳
    private String pnrTime;
    //证联支付的流水号
    private String pnrSeqId;
    //其他
    private String other;


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

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ZlrtStatementLineItem{" +
                "instuId='" + instuId + '\'' +
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
                ", certId='" + certId + '\'' +
                ", transAmt='" + transAmt + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", fundType='" + fundType + '\'' +
                ", pnrDate='" + pnrDate + '\'' +
                ", pnrTime='" + pnrTime + '\'' +
                ", pnrSeqId='" + pnrSeqId + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
