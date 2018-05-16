package com.jinhui.scheduler.biz.zlrt.dto;

/**
 * 结算类业务(监管银行)  申购结算业务的映射对象
 *
 *  wsc
 *  2017-11-15 14:20
 **/
public class PurchaseLineItem {

    //商户的系统日期，YYYYMMDD
    private String fundDate;
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

    //基金交易金额
    private String transAmt;
    //交易结算日期，YYYYMMDD；不小于交易日期
    private String fundCode;
    //基金类型
    private String fundType;

    private String fundUserId;

    private String resv;

    public String getFundDate() {
        return fundDate;
    }

    public void setFundDate(String fundDate) {
        this.fundDate = fundDate;
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

    public String getFundUserId() {
        return fundUserId;
    }

    public void setFundUserId(String fundUserId) {
        this.fundUserId = fundUserId;
    }

    public String getResv() {
        return resv;
    }

    public void setResv(String resv) {
        this.resv = resv;
    }

    @Override
    public String toString() {
        return "PurchaseLineItem{" +
                "fundDate='" + fundDate + '\'' +
                ", fundSeqId='" + fundSeqId + '\'' +
                ", busiType='" + busiType + '\'' +
                ", userId='" + userId + '\'' +
                ", userNameText='" + userNameText + '\'' +
                ", certType='" + certType + '\'' +
                ", certId='" + certId + '\'' +
                ", bankType='" + bankType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", transAmt='" + transAmt + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", fundType='" + fundType + '\'' +
                ", fundUserId='" + fundUserId + '\'' +
                ", resv='" + resv + '\'' +
                '}';
    }
}
