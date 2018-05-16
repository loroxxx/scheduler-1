package com.jinhui.scheduler.biz.imiqian.dto;

/**
 * 开户申请文件读取的映射对象
 *
 *  wsc
 *  2017-05-19 14:20
 **/
public class AmqTradeApplyLineItem {
    //申请单编号
    private String appSheetSerialNo;
    //基金代码
    private String fundCode;
    //巨额赎回处理标志
    private String largeRedemptionFlag;
    //交易发生日期
    private String transactionDate;
    //交易发生时间
    private String transactionTime;
    //投资人基金交易帐号
    private String transactionAccountID;
    //销售人代码
    private String distributorCode;
    //投资人基金交易帐号
    private String applicationVol;
    //销售人代码
    private String applicationAmount;
    //业务代码
    private String businessCode;
    //网点号码
    private String branchCode;
    //投资人基金帐号
    private String taAccountID;
    //结算币种
    private String currencyType;
    //个人/机构标志
    private String individualOrInstitution;
    //收费类别
    private String shareClass ;
    //强制赎回类型
    private String forceRedemptionType ;
    //带走收益标志
    private String takeIncomeFlag;
    //巨额购买处理标志
    private String largeBuyFlag  ;
    //收费类型
    private String chargeType ;
    //指定费率
    private String specifyRateFee;
    //指定费用
    private String SpecifyFee ;
    //手续费
    private String Charge;
    //商户系统流水号
    private String zlFundSeqId;
    //赎回标识
    private String redeemFlag;
    //银行代码
    private String transBankCode;
    //银行账号
    private String transBankAct;

    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getLargeRedemptionFlag() {
        return largeRedemptionFlag;
    }

    public void setLargeRedemptionFlag(String largeRedemptionFlag) {
        this.largeRedemptionFlag = largeRedemptionFlag;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionAccountID() {
        return transactionAccountID;
    }

    public void setTransactionAccountID(String transactionAccountID) {
        this.transactionAccountID = transactionAccountID;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getApplicationVol() {
        return applicationVol;
    }

    public void setApplicationVol(String applicationVol) {
        this.applicationVol = applicationVol;
    }

    public String getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(String applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getTaAccountID() {
        return taAccountID;
    }

    public void setTaAccountID(String taAccountID) {
        this.taAccountID = taAccountID;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getIndividualOrInstitution() {
        return individualOrInstitution;
    }

    public void setIndividualOrInstitution(String individualOrInstitution) {
        this.individualOrInstitution = individualOrInstitution;
    }

    public String getShareClass() {
        return shareClass;
    }

    public void setShareClass(String shareClass) {
        this.shareClass = shareClass;
    }

    public String getForceRedemptionType() {
        return forceRedemptionType;
    }

    public void setForceRedemptionType(String forceRedemptionType) {
        this.forceRedemptionType = forceRedemptionType;
    }

    public String getTakeIncomeFlag() {
        return takeIncomeFlag;
    }

    public void setTakeIncomeFlag(String takeIncomeFlag) {
        this.takeIncomeFlag = takeIncomeFlag;
    }

    public String getLargeBuyFlag() {
        return largeBuyFlag;
    }

    public void setLargeBuyFlag(String largeBuyFlag) {
        this.largeBuyFlag = largeBuyFlag;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getSpecifyRateFee() {
        return specifyRateFee;
    }

    public void setSpecifyRateFee(String specifyRateFee) {
        this.specifyRateFee = specifyRateFee;
    }

    public String getSpecifyFee() {
        return SpecifyFee;
    }

    public void setSpecifyFee(String specifyFee) {
        SpecifyFee = specifyFee;
    }

    public String getCharge() {
        return Charge;
    }

    public void setCharge(String charge) {
        Charge = charge;
    }

    public String getZlFundSeqId() {
        return zlFundSeqId;
    }

    public void setZlFundSeqId(String zlFundSeqId) {
        this.zlFundSeqId = zlFundSeqId;
    }

    public String getRedeemFlag() {
        return redeemFlag;
    }

    public void setRedeemFlag(String redeemFlag) {
        this.redeemFlag = redeemFlag;
    }

    public String getTransBankCode() {
        return transBankCode;
    }

    public void setTransBankCode(String transBankCode) {
        this.transBankCode = transBankCode;
    }

    public String getTransBankAct() {
        return transBankAct;
    }

    public void setTransBankAct(String transBankAct) {
        this.transBankAct = transBankAct;
    }

    @Override
    public String toString() {
        return "AmqTradeApplyLineItem{" +
                "appSheetSerialNo='" + appSheetSerialNo + '\'' +
                ", fundCode='" + fundCode + '\'' +
                ", largeRedemptionFlag='" + largeRedemptionFlag + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", transactionAccountID='" + transactionAccountID + '\'' +
                ", distributorCode='" + distributorCode + '\'' +
                ", applicationVol='" + applicationVol + '\'' +
                ", applicationAmount='" + applicationAmount + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", taAccountID='" + taAccountID + '\'' +
                ", currencyType='" + currencyType + '\'' +
                ", individualOrInstitution='" + individualOrInstitution + '\'' +
                ", shareClass='" + shareClass + '\'' +
                ", forceRedemptionType='" + forceRedemptionType + '\'' +
                ", takeIncomeFlag='" + takeIncomeFlag + '\'' +
                ", largeBuyFlag='" + largeBuyFlag + '\'' +
                ", chargeType='" + chargeType + '\'' +
                ", specifyRateFee='" + specifyRateFee + '\'' +
                ", SpecifyFee='" + SpecifyFee + '\'' +
                ", Charge='" + Charge + '\'' +
                ", zlFundSeqId='" + zlFundSeqId + '\'' +
                ", redeemFlag='" + redeemFlag + '\'' +
                ", transBankCode='" + transBankCode + '\'' +
                ", transBankAct='" + transBankAct + '\'' +
                '}';
    }
}
