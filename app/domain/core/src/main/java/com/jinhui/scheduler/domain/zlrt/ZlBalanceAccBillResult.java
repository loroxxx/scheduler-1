package com.jinhui.scheduler.domain.zlrt;

import java.math.BigDecimal;
import java.util.Date;

public class ZlBalanceAccBillResult {
    private Integer id;

    private Integer batchCode;

    private String fundDate;

    private String fundTime;

    private String userName;

    private String userId;

    private String investorId;

    private String fundCode;

    private String transBankAct;

    private String transBankCode;

    private String busiType;

    private String zlBusiType;

    private BigDecimal transAmt;

    private BigDecimal zlTransAmt;

    private String transState;

    private String zlTransState;

    private String fundSeqId;

    private String zlFundSeqId;

    private String notEqualFlag;

    private String resultType;

    private String isCheck;

    private String resultDesc;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(Integer batchCode) {
        this.batchCode = batchCode;
    }

    public String getFundDate() {
        return fundDate;
    }

    public void setFundDate(String fundDate) {
        this.fundDate = fundDate == null ? null : fundDate.trim();
    }

    public String getFundTime() {
        return fundTime;
    }

    public void setFundTime(String fundTime) {
        this.fundTime = fundTime == null ? null : fundTime.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId == null ? null : investorId.trim();
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    public String getTransBankAct() {
        return transBankAct;
    }

    public void setTransBankAct(String transBankAct) {
        this.transBankAct = transBankAct == null ? null : transBankAct.trim();
    }

    public String getTransBankCode() {
        return transBankCode;
    }

    public void setTransBankCode(String transBankCode) {
        this.transBankCode = transBankCode == null ? null : transBankCode.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getZlBusiType() {
        return zlBusiType;
    }

    public void setZlBusiType(String zlBusiType) {
        this.zlBusiType = zlBusiType == null ? null : zlBusiType.trim();
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    public BigDecimal getZlTransAmt() {
        return zlTransAmt;
    }

    public void setZlTransAmt(BigDecimal zlTransAmt) {
        this.zlTransAmt = zlTransAmt;
    }

    public String getTransState() {
        return transState;
    }

    public void setTransState(String transState) {
        this.transState = transState == null ? null : transState.trim();
    }

    public String getZlTransState() {
        return zlTransState;
    }

    public void setZlTransState(String zlTransState) {
        this.zlTransState = zlTransState == null ? null : zlTransState.trim();
    }

    public String getFundSeqId() {
        return fundSeqId;
    }

    public void setFundSeqId(String fundSeqId) {
        this.fundSeqId = fundSeqId == null ? null : fundSeqId.trim();
    }

    public String getZlFundSeqId() {
        return zlFundSeqId;
    }

    public void setZlFundSeqId(String zlFundSeqId) {
        this.zlFundSeqId = zlFundSeqId == null ? null : zlFundSeqId.trim();
    }

    public String getNotEqualFlag() {
        return notEqualFlag;
    }

    public void setNotEqualFlag(String notEqualFlag) {
        this.notEqualFlag = notEqualFlag == null ? null : notEqualFlag.trim();
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType == null ? null : resultType.trim();
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck == null ? null : isCheck.trim();
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}