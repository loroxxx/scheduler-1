package com.jinhui.scheduler.domain.imiqian.pojo;


/**
 * Created by Administrator on 2017/3/5.
 */
public class JzAccountInfo {
    //主键
    private String Id;
    //申请单编号
    private String AppSheetSerialNo;
    //证件类型
    private String CertificateType;
    //证件号码
    private String CertificateNo;
    //姓名
    private String InvestorName;
    //交易发生日期
    private String TransactionDate;
    //交易发生时间
    private String TransactionTime;
    //个人/机构标识
    private String IndividualOrInstitution;
    //用户ID
    private String TransactionAccountID;
    //销售机构代码
    private String DistributorCode;
    //业务代码
    private String BusinessCode;
    //邮箱地址
    private String EmailAddress;
    //手机号码
    private String MobileTelNo;
    //网点号码
    private String BranchCode;
    //客户风险等级
    private String ClientRiskRate;
    //确认标识
    private String ConfirmFlag;
    //确认日期
    private String TransactionCfmDate;
    //返回码
    private String ReturnCode;
    //状态
    private String Status;
    //基金账号
    private String TAAccountID;
    //确认流水号
    private String TASerialNO;
    //错误详情
    private String ErrorDetail;
    //页号
    private int PageNum;
    //开始索引
    private int Offset;
    //每页记录数
    private int PageSize;

    public String getAppSheetSerialNo() {
        return AppSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        AppSheetSerialNo = appSheetSerialNo;
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

    public String getIndividualOrInstitution() {
        return IndividualOrInstitution;
    }

    public void setIndividualOrInstitution(String individualOrInstitution) {
        IndividualOrInstitution = individualOrInstitution;
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

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getMobileTelNo() {
        return MobileTelNo;
    }

    public void setMobileTelNo(String mobileTelNo) {
        MobileTelNo = mobileTelNo;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getClientRiskRate() {
        return ClientRiskRate;
    }

    public void setClientRiskRate(String clientRiskRate) {
        ClientRiskRate = clientRiskRate;
    }

    public String getConfirmFlag() {
        return ConfirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        ConfirmFlag = confirmFlag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getErrorDetail() {
        return ErrorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        ErrorDetail = errorDetail;
    }

    public int getOffset() {
        return Offset;
    }

    public void setOffset(int offset) {
        Offset = offset;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    @Override
    public String toString() {
        return "JzAccountApplyLineItem{" +
                "AppSheetSerialNo='" + AppSheetSerialNo + '\'' +
                ", CertificateType='" + CertificateType + '\'' +
                ", CertificateNo='" + CertificateNo + '\'' +
                ", InvestorName='" + InvestorName + '\'' +
                ", TransactionDate='" + TransactionDate + '\'' +
                ", TransactionTime='" + TransactionTime + '\'' +
                ", IndividualOrInstitution='" + IndividualOrInstitution + '\'' +
                ", TransactionAccountID='" + TransactionAccountID + '\'' +
                ", DistributorCode='" + DistributorCode + '\'' +
                ", BusinessCode='" + BusinessCode + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", MobileTelNo='" + MobileTelNo + '\'' +
                ", BranchCode='" + BranchCode + '\'' +
                ", ClientRiskRate='" + ClientRiskRate + '\'' +
                '}';
    }
}
