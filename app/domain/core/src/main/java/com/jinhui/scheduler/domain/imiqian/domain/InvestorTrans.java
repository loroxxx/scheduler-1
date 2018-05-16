package com.jinhui.scheduler.domain.imiqian.domain;

public class InvestorTrans {
    private Integer id;

    private String serialNumber;

    private int batchCode;

    private String investorId;

    private String chnId;

    private String name;

    private String transTime;

    private String confirmDate;

    private String appSheetSerialNo;

    private String productNo;

    private String childProductNo;

    private String productName;

    private String transType;

    private Double transVol;

    private Double transAmount;

    private Double productVol;

    private Double transFix;

    private String takeIncomeFlag;

    private String hugeSubsFlag;

    private String hugeRedemFlag;

    private String chnCode;

    private String riskDisclosure;

    private String transBankName;

    private String chargeType;

    private String chargeWay;

    private Double chargeRate;

    private Double specifyFee;

    private String rollingFlag;

    private String forceRedemReason;

    private String forceRedemType;

    private String transState;

    private String isExcess;

    private String returnCode;

    private String errorMsg;

    private String createTime;

    private String updateTime;

    private Double totalPostionAmount;


    private Double totalRedemedAmount;

    private Double totalSubsAmount;

    //商户系统流水号
    private String zlFundSeqId;
    //赎回标识
    private String redeemFlag;
    //银行代码
    private String transBankCode;
    //银行账号
    private String transBankAct;

    //业务汇总数量
    private String num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getChnId() {
        return chnId;
    }

    public void setChnId(String chnId) {
        this.chnId = chnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getChildProductNo() {
        return childProductNo;
    }

    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
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

    public Double getProductVol() {
        return productVol;
    }

    public void setProductVol(Double productVol) {
        this.productVol = productVol;
    }

    public Double getTransFix() {
        return transFix;
    }

    public void setTransFix(Double transFix) {
        this.transFix = transFix;
    }

    public String getTakeIncomeFlag() {
        return takeIncomeFlag;
    }

    public void setTakeIncomeFlag(String takeIncomeFlag) {
        this.takeIncomeFlag = takeIncomeFlag;
    }

    public String getHugeSubsFlag() {
        return hugeSubsFlag;
    }

    public void setHugeSubsFlag(String hugeSubsFlag) {
        this.hugeSubsFlag = hugeSubsFlag;
    }

    public String getHugeRedemFlag() {
        return hugeRedemFlag;
    }

    public void setHugeRedemFlag(String hugeRedemFlag) {
        this.hugeRedemFlag = hugeRedemFlag;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getRiskDisclosure() {
        return riskDisclosure;
    }

    public void setRiskDisclosure(String riskDisclosure) {
        this.riskDisclosure = riskDisclosure;
    }

    public String getTransBankName() {
        return transBankName;
    }

    public void setTransBankName(String transBankName) {
        this.transBankName = transBankName;
    }

    public String getTransBankAct() {
        return transBankAct;
    }

    public void setTransBankAct(String transBankAct) {
        this.transBankAct = transBankAct;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeWay() {
        return chargeWay;
    }

    public void setChargeWay(String chargeWay) {
        this.chargeWay = chargeWay;
    }

    public Double getChargeRate() {
        return chargeRate;
    }

    public void setChargeRate(Double chargeRate) {
        this.chargeRate = chargeRate;
    }

    public Double getSpecifyFee() {
        return specifyFee;
    }

    public void setSpecifyFee(Double specifyFee) {
        this.specifyFee = specifyFee;
    }

    public String getRollingFlag() {
        return rollingFlag;
    }

    public void setRollingFlag(String rollingFlag) {
        this.rollingFlag = rollingFlag;
    }

    public String getForceRedemReason() {
        return forceRedemReason;
    }

    public void setForceRedemReason(String forceRedemReason) {
        this.forceRedemReason = forceRedemReason;
    }

    public String getForceRedemType() {
        return forceRedemType;
    }

    public void setForceRedemType(String forceRedemType) {
        this.forceRedemType = forceRedemType;
    }

    public String getTransState() {
        return transState;
    }

    public void setTransState(String transState) {
        this.transState = transState;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Double getTotalPostionAmount() {
        return totalPostionAmount;
    }

    public void setTotalPostionAmount(Double totalPostionAmount) {
        this.totalPostionAmount = totalPostionAmount;
    }

    public Double getTotalRedemedAmount() {
        return totalRedemedAmount;
    }

    public void setTotalRedemedAmount(Double totalRedemedAmount) {
        this.totalRedemedAmount = totalRedemedAmount;
    }

    public Double getTotalSubsAmount() {
        return totalSubsAmount;
    }

    public void setTotalSubsAmount(Double totalSubsAmount) {
        this.totalSubsAmount = totalSubsAmount;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIsExcess() {
        return isExcess;
    }

    public void setIsExcess(String isExcess) {
        this.isExcess = isExcess;
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

    @Override
    public String toString() {
        return "InvestorTrans{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", batchCode=" + batchCode +
                ", investorId='" + investorId + '\'' +
                ", chnId='" + chnId + '\'' +
                ", name='" + name + '\'' +
                ", transTime='" + transTime + '\'' +
                ", confirmDate='" + confirmDate + '\'' +
                ", appSheetSerialNo='" + appSheetSerialNo + '\'' +
                ", productNo='" + productNo + '\'' +
                ", childProductNo='" + childProductNo + '\'' +
                ", productName='" + productName + '\'' +
                ", transType='" + transType + '\'' +
                ", transVol=" + transVol +
                ", transAmount=" + transAmount +
                ", productVol=" + productVol +
                ", transFix=" + transFix +
                ", takeIncomeFlag='" + takeIncomeFlag + '\'' +
                ", hugeSubsFlag='" + hugeSubsFlag + '\'' +
                ", hugeRedemFlag='" + hugeRedemFlag + '\'' +
                ", chnCode='" + chnCode + '\'' +
                ", riskDisclosure='" + riskDisclosure + '\'' +
                ", transBankName='" + transBankName + '\'' +
                ", chargeType='" + chargeType + '\'' +
                ", chargeWay='" + chargeWay + '\'' +
                ", chargeRate=" + chargeRate +
                ", specifyFee=" + specifyFee +
                ", rollingFlag='" + rollingFlag + '\'' +
                ", forceRedemReason='" + forceRedemReason + '\'' +
                ", forceRedemType='" + forceRedemType + '\'' +
                ", transState='" + transState + '\'' +
                ", isExcess='" + isExcess + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", totalPostionAmount=" + totalPostionAmount +
                ", totalRedemedAmount=" + totalRedemedAmount +
                ", totalSubsAmount=" + totalSubsAmount +
                ", zlFundSeqId='" + zlFundSeqId + '\'' +
                ", redeemFlag='" + redeemFlag + '\'' +
                ", transBankCode='" + transBankCode + '\'' +
                ", transBankAct='" + transBankAct + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}