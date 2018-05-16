package com.jinhui.scheduler.biz.zlrt.dto;

/**
 * 证联赎回类业务的映射对象
 *
 *  wsc
 *  2017-11-15 14:20
 **/
public class RedeemLineItem {

    //业务类型
    private String businessType;
    //结算日期
    private String settleDate;
    //流水号
    private String serialNumber;
    //系统日期
    private String systemDate;


    //证联支付分配给商户的机构代码
    private String instuId;
    //商户的系统日期，YYYYMMDD
    private String fundDate;
    //商户的时间戳，HHMMSS
    private String fundTime;
    //商户系统流水号。需要保证一个交易日中流水号的唯一性
    private String fundSeqId;
    //业务类型:赎回0004、分红 0005、退款0006
    private String busiType;
    //用户在证联支付平台里的客户号
    private String userId;
    //客户的姓名
    private String userNameText;
    //证件类型
    private String certType;
    //证件号码
    private String certId;

    private String bankType;

    private String cardNo;

    private String flag;

    //基金交易金额
    private String transAmt;
    //交易结算日期，YYYYMMDD；不小于交易日期
    private String liqDate;
    //基金申购、赎回、分红交易中，基金公司发行的基金代码
    private String fundCode;
    //基金类型
    private String fundType;
    //撤单原始基金申购交易日期，YYYYMMDD
    private String orgFundDate;
    //撤单原始基金申购交易流水号
    private String orgFundSeqId;

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

    public String getFundSeqId() {
        return fundSeqId;
    }

    public void setFundSeqId(String fundSeqId) {
        this.fundSeqId = fundSeqId;
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

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getLiqDate() {
        return liqDate;
    }

    public void setLiqDate(String liqDate) {
        this.liqDate = liqDate;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "RedeemLineItem{" +
                "businessType='" + businessType + '\'' +
                ", settleDate='" + settleDate + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", systemDate='" + systemDate + '\'' +
                ", instuId='" + instuId + '\'' +
                ", fundDate='" + fundDate + '\'' +
                ", fundTime='" + fundTime + '\'' +
                ", fundSeqId='" + fundSeqId + '\'' +
                ", busiType='" + busiType + '\'' +
                ", userId='" + userId + '\'' +
                ", userNameText='" + userNameText + '\'' +
                ", certType='" + certType + '\'' +
                ", certId='" + certId + '\'' +
                ", bankType='" + bankType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", flag='" + flag + '\'' +
                ", transAmt='" + transAmt + '\'' +
                ", liqDate='" + liqDate + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", fundType='" + fundType + '\'' +
                ", orgFundDate='" + orgFundDate + '\'' +
                ", orgFundSeqId='" + orgFundSeqId + '\'' +
                '}';
    }
}
