package com.jinhui.scheduler.biz.gzefe.pojo;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
public class AccountApplyInfo {

    //通讯地址
    private String Address;
    //法人代表身份证件代码
    private String InstReprIDCode;
    //法人代表证件类型
    private String InstReprIDType;
    //法人代表姓名
    private String InstReprName;
    //申请单编号
    private String AppSheetSerialNo;
    //个人证件类型及机构证件型
    private String CertificateType;
    // 投资人证件号码
    private String CertificateNo;
    // 投资人户名
    private String InvestorName;
    // 交易发生日期
    private String TransactionDate;
    // 交易发生时间
    private String TransactionTime;
    // 个人/机构标志
    private String IndividualOrInstitution;
    // 投资人邮政编码
    private String PostCode;
    // 投资人基金交易帐号
    private String TransactionAccountID;
    //销售人代码
    private String DistributorCode;
    //业务代码
    private String BusinessCode;
    //投资人在销售人处用于交易的资金帐号
    private String DepositAcct;
    //投资人传真号码
    private String FaxNo;
    //投资人手机号码
    private String MobileTelNo;
    // 投资人收款银行账户户名
    private String AcctNameOfInvestorInClearingAgency;
    //投资人收款银行账户账号
    private String AcctNoOfInvestorInClearingAgency;
    //投资人收款银行账户开户行
    private String ClearingAgency;
    // 客户风险等级
    private String ClientRiskRate;


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getInstReprIDCode() {
        return InstReprIDCode;
    }

    public void setInstReprIDCode(String instReprIDCode) {
        InstReprIDCode = instReprIDCode;
    }

    public String getInstReprIDType() {
        return InstReprIDType;
    }

    public void setInstReprIDType(String instReprIDType) {
        InstReprIDType = instReprIDType;
    }

    public String getInstReprName() {
        return InstReprName;
    }

    public void setInstReprName(String instReprName) {
        InstReprName = instReprName;
    }

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

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
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

    public String getDepositAcct() {
        return DepositAcct;
    }

    public void setDepositAcct(String depositAcct) {
        DepositAcct = depositAcct;
    }

    public String getFaxNo() {
        return FaxNo;
    }

    public void setFaxNo(String faxNo) {
        FaxNo = faxNo;
    }

    public String getMobileTelNo() {
        return MobileTelNo;
    }

    public void setMobileTelNo(String mobileTelNo) {
        MobileTelNo = mobileTelNo;
    }

    public String getAcctNameOfInvestorInClearingAgency() {
        return AcctNameOfInvestorInClearingAgency;
    }

    public void setAcctNameOfInvestorInClearingAgency(String acctNameOfInvestorInClearingAgency) {
        AcctNameOfInvestorInClearingAgency = acctNameOfInvestorInClearingAgency;
    }

    public String getAcctNoOfInvestorInClearingAgency() {
        return AcctNoOfInvestorInClearingAgency;
    }

    public void setAcctNoOfInvestorInClearingAgency(String acctNoOfInvestorInClearingAgency) {
        AcctNoOfInvestorInClearingAgency = acctNoOfInvestorInClearingAgency;
    }

    public String getClearingAgency() {
        return ClearingAgency;
    }

    public void setClearingAgency(String clearingAgency) {
        ClearingAgency = clearingAgency;
    }

    public String getClientRiskRate() {
        return ClientRiskRate;
    }

    public void setClientRiskRate(String clientRiskRate) {
        ClientRiskRate = clientRiskRate;
    }

    @Override
    public String toString() {
        return "AccountApplyInfo{" +
                "Address='" + Address + '\'' +
                ", InstReprIDCode='" + InstReprIDCode + '\'' +
                ", InstReprIDType='" + InstReprIDType + '\'' +
                ", InstReprName='" + InstReprName + '\'' +
                ", AppSheetSerialNo='" + AppSheetSerialNo + '\'' +
                ", CertificateType='" + CertificateType + '\'' +
                ", CertificateNo='" + CertificateNo + '\'' +
                ", InvestorName='" + InvestorName + '\'' +
                ", TransactionDate='" + TransactionDate + '\'' +
                ", TransactionTime='" + TransactionTime + '\'' +
                ", IndividualOrInstitution='" + IndividualOrInstitution + '\'' +
                ", PostCode='" + PostCode + '\'' +
                ", TransactionAccountID='" + TransactionAccountID + '\'' +
                ", DistributorCode='" + DistributorCode + '\'' +
                ", BusinessCode='" + BusinessCode + '\'' +
                ", DepositAcct='" + DepositAcct + '\'' +
                ", FaxNo='" + FaxNo + '\'' +
                ", MobileTelNo='" + MobileTelNo + '\'' +
                ", AcctNameOfInvestorInClearingAgency='" + AcctNameOfInvestorInClearingAgency + '\'' +
                ", AcctNoOfInvestorInClearingAgency='" + AcctNoOfInvestorInClearingAgency + '\'' +
                ", ClearingAgency='" + ClearingAgency + '\'' +
                ", ClientRiskRate='" + ClientRiskRate + '\'' +
                '}';
    }
}
