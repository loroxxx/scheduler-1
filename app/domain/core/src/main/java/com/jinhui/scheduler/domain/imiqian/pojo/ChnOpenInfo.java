package com.jinhui.scheduler.domain.imiqian.pojo;

/**
 * create by wsc  2017-05-22 18:27
 **/
public class ChnOpenInfo {
    //申请单编号
    private String AppSheetSerialNo;
    //交易确认日期
    private String TransactionCfmDate;
    //交易处理返回代码
    private String ReturnCode;
    //投资人基金交易帐号
    private String TransactionAccountID;
    //销售人代码
    private String DistributorCode;
    //业务代码
    private String BusinessCode;
    //投资人基金帐号
    private String  TAAccountID;
    //TA确认交易流水号
    private String TASerialNO;
    //交易发生日期
    private String TransactionDate;
    //交易发生时间
    private String TransactionTime;
    //网点号码
    private String BranchCode;
    //个人证件类型及机构证件型
    private String CertificateType;
    //投资人证件号码
    private String CertificateNo;
    //投资人户名
    private String InvestorName;
    //个人/机构标志
    private String IndividualOrInstitution;

    //非必输项目
    //是否注册登记人发起业务标志
    private String FromTAFlag;
    //多渠道开户标志
    private String MultiAcctFlag;
    //投资人户名简称
    private String AccountAbbr;
    //基金账户卡的凭证号
    private String AccountCardID;
    //交易所在地区编号
    private String RegionCode;
    //对方销售人处投资人基金交易帐号
    private String TargetTransactionAccountID;
    //操作（清算）网点编号
    private String NetNo;
    //摘要/说明
    private String Specification;
    //TA客户编号
    private String CustomerNo;
    //冻结原因
    private String FrozenCause;
    //冻结截止日期
    private String FreezingDeadline;
    //出错详细信息
    private String ErrorDetail;

    public String getAppSheetSerialNo() {
        return AppSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        AppSheetSerialNo = appSheetSerialNo;
    }

    public String getTransactionCfmDate() {
        return TransactionCfmDate;
    }

    public void setTransactionCfmDate(String transactionCfmDate) {
        TransactionCfmDate = transactionCfmDate;
    }

    public String getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(String returnCode) {
        ReturnCode = returnCode;
    }

    public String getTransactionAccountID() {
        return TransactionAccountID;
    }

    public void setTransactionAccountID(String transactionAccountID) {
        TransactionAccountID = transactionAccountID;
    }

    public String getDistributorCode() {
        return DistributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        DistributorCode = distributorCode;
    }

    public String getBusinessCode() {
        return BusinessCode;
    }

    public void setBusinessCode(String businessCode) {
        BusinessCode = businessCode;
    }

    public String getTAAccountID() {
        return TAAccountID;
    }

    public void setTAAccountID(String TAAccountID) {
        this.TAAccountID = TAAccountID;
    }

    public String getTASerialNO() {
        return TASerialNO;
    }

    public void setTASerialNO(String TASerialNO) {
        this.TASerialNO = TASerialNO;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return TransactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        TransactionTime = transactionTime;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getCertificateType() {
        return CertificateType;
    }

    public void setCertificateType(String certificateType) {
        CertificateType = certificateType;
    }

    public String getCertificateNo() {
        return CertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        CertificateNo = certificateNo;
    }

    public String getInvestorName() {
        return InvestorName;
    }

    public void setInvestorName(String investorName) {
        InvestorName = investorName;
    }

    public String getIndividualOrInstitution() {
        return IndividualOrInstitution;
    }

    public void setIndividualOrInstitution(String individualOrInstitution) {
        IndividualOrInstitution = individualOrInstitution;
    }

    public String getFromTAFlag() {
        return FromTAFlag;
    }

    public void setFromTAFlag(String fromTAFlag) {
        FromTAFlag = fromTAFlag;
    }

    public String getMultiAcctFlag() {
        return MultiAcctFlag;
    }

    public void setMultiAcctFlag(String multiAcctFlag) {
        MultiAcctFlag = multiAcctFlag;
    }

    public String getAccountAbbr() {
        return AccountAbbr;
    }

    public void setAccountAbbr(String accountAbbr) {
        AccountAbbr = accountAbbr;
    }

    public String getAccountCardID() {
        return AccountCardID;
    }

    public void setAccountCardID(String accountCardID) {
        AccountCardID = accountCardID;
    }

    public String getRegionCode() {
        return RegionCode;
    }

    public void setRegionCode(String regionCode) {
        RegionCode = regionCode;
    }

    public String getTargetTransactionAccountID() {
        return TargetTransactionAccountID;
    }

    public void setTargetTransactionAccountID(String targetTransactionAccountID) {
        TargetTransactionAccountID = targetTransactionAccountID;
    }

    public String getNetNo() {
        return NetNo;
    }

    public void setNetNo(String netNo) {
        NetNo = netNo;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(String customerNo) {
        CustomerNo = customerNo;
    }

    public String getFrozenCause() {
        return FrozenCause;
    }

    public void setFrozenCause(String frozenCause) {
        FrozenCause = frozenCause;
    }

    public String getFreezingDeadline() {
        return FreezingDeadline;
    }

    public void setFreezingDeadline(String freezingDeadline) {
        FreezingDeadline = freezingDeadline;
    }

    public String getErrorDetail() {
        return ErrorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        ErrorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return "ChnOpenInfo{" +
                "AppSheetSerialNo='" + AppSheetSerialNo + '\'' +
                ", TransactionCfmDate='" + TransactionCfmDate + '\'' +
                ", ReturnCode='" + ReturnCode + '\'' +
                ", TransactionAccountID='" + TransactionAccountID + '\'' +
                ", DistributorCode='" + DistributorCode + '\'' +
                ", BusinessCode='" + BusinessCode + '\'' +
                ", TAAccountID='" + TAAccountID + '\'' +
                ", TASerialNO='" + TASerialNO + '\'' +
                ", TransactionDate='" + TransactionDate + '\'' +
                ", TransactionTime='" + TransactionTime + '\'' +
                ", BranchCode='" + BranchCode + '\'' +
                ", CertificateType='" + CertificateType + '\'' +
                ", CertificateNo='" + CertificateNo + '\'' +
                ", InvestorName='" + InvestorName + '\'' +
                ", IndividualOrInstitution='" + IndividualOrInstitution + '\'' +
                ", FromTAFlag='" + FromTAFlag + '\'' +
                ", MultiAcctFlag='" + MultiAcctFlag + '\'' +
                ", AccountAbbr='" + AccountAbbr + '\'' +
                ", AccountCardID='" + AccountCardID + '\'' +
                ", RegionCode='" + RegionCode + '\'' +
                ", TargetTransactionAccountID='" + TargetTransactionAccountID + '\'' +
                ", NetNo='" + NetNo + '\'' +
                ", Specification='" + Specification + '\'' +
                ", CustomerNo='" + CustomerNo + '\'' +
                ", FrozenCause='" + FrozenCause + '\'' +
                ", FreezingDeadline='" + FreezingDeadline + '\'' +
                ", ErrorDetail='" + ErrorDetail + '\'' +
                '}';
    }
}
