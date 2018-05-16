package com.jinhui.scheduler.biz.gzefe.pojo;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public class IncomeInfo {

    private String AppSheetSerialNo;//流水号
    private String BasisforCalculatingDividend;//红利/红利再投资基数
    private String TransactionCfmDate;//交易确认日期
    private String CertificateType;//个人证件类型及机构证件型
    private String CertificateNo;//投资人证件号码
    private String MobileTelNo;//投资人手机号码
    private String CurrencyType;//结算币种
    private String VolOfDividendforReinvestment;//基金账户红利再投资基金份数
    private String DividentDate;//分红日/发放日
    private String DividendAmount;//基金账户红利资金
    private String XRDate;//除权日
    private String ConfirmedAmount;//每笔交易确认金额
    private String FundCode;//基金代码
    private String CodeOfTargetFund;//转换时的目标基金代码
    private String RegistrationDate;//权益登记日期
    private String ReturnCode;//交易处理返回代码
    private String TransactionAccountID;//投资人基金交易账号
    private String DistributorCode;//销售人代码
    private String BusinessCode;//业务代码
    private String TAAccountID;//投资人基金账号
    private String DepositAcct;//投资人在销售人处用于交易的资金帐号
    private String DividendPerUnit;//单位基金分红金额（含税 ）
    private String DefDividendMethod;//默认分红方式
    private String DividendType;//分红类型
    private String OtherFee2;//其他费用2

    public String getAppSheetSerialNo() {
        return AppSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        AppSheetSerialNo = appSheetSerialNo;
    }

    public String getBasisforCalculatingDividend() {
        return BasisforCalculatingDividend;
    }

    public void setBasisforCalculatingDividend(String basisforCalculatingDividend) {
        BasisforCalculatingDividend = basisforCalculatingDividend;
    }

    public String getTransactionCfmDate() {
        return TransactionCfmDate;
    }

    public void setTransactionCfmDate(String transactionCfmDate) {
        TransactionCfmDate = transactionCfmDate;
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

    public String getMobileTelNo() {
        return MobileTelNo;
    }

    public void setMobileTelNo(String mobileTelNo) {
        MobileTelNo = mobileTelNo;
    }

    public String getCurrencyType() {
        return CurrencyType;
    }

    public void setCurrencyType(String currencyType) {
        CurrencyType = currencyType;
    }

    public String getVolOfDividendforReinvestment() {
        return VolOfDividendforReinvestment;
    }

    public void setVolOfDividendforReinvestment(String volOfDividendforReinvestment) {
        VolOfDividendforReinvestment = volOfDividendforReinvestment;
    }

    public String getDividentDate() {
        return DividentDate;
    }

    public void setDividentDate(String dividentDate) {
        DividentDate = dividentDate;
    }

    public String getDividendAmount() {
        return DividendAmount;
    }

    public void setDividendAmount(String dividendAmount) {
        DividendAmount = dividendAmount;
    }

    public String getXRDate() {
        return XRDate;
    }

    public void setXRDate(String XRDate) {
        this.XRDate = XRDate;
    }

    public String getConfirmedAmount() {
        return ConfirmedAmount;
    }

    public void setConfirmedAmount(String confirmedAmount) {
        ConfirmedAmount = confirmedAmount;
    }

    public String getFundCode() {
        return FundCode;
    }

    public void setFundCode(String fundCode) {
        FundCode = fundCode;
    }

    public String getCodeOfTargetFund() {
        return CodeOfTargetFund;
    }

    public void setCodeOfTargetFund(String codeOfTargetFund) {
        CodeOfTargetFund = codeOfTargetFund;
    }

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
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

    public String getDepositAcct() {
        return DepositAcct;
    }

    public void setDepositAcct(String depositAcct) {
        DepositAcct = depositAcct;
    }

    public String getDividendPerUnit() {
        return DividendPerUnit;
    }

    public void setDividendPerUnit(String dividendPerUnit) {
        DividendPerUnit = dividendPerUnit;
    }

    public String getDefDividendMethod() {
        return DefDividendMethod;
    }

    public void setDefDividendMethod(String defDividendMethod) {
        DefDividendMethod = defDividendMethod;
    }

    public String getDividendType() {
        return DividendType;
    }

    public void setDividendType(String dividendType) {
        DividendType = dividendType;
    }

    public String getOtherFee2() {
        return OtherFee2;
    }

    public void setOtherFee2(String otherFee2) {
        OtherFee2 = otherFee2;
    }


    @Override
    public String toString() {
        return "IncomeInfo{" +
                "AppSheetSerialNo='" + AppSheetSerialNo + '\'' +
                ", BasisforCalculatingDividend='" + BasisforCalculatingDividend + '\'' +
                ", TransactionCfmDate='" + TransactionCfmDate + '\'' +
                ", CertificateType='" + CertificateType + '\'' +
                ", CertificateNo='" + CertificateNo + '\'' +
                ", MobileTelNo='" + MobileTelNo + '\'' +
                ", CurrencyType='" + CurrencyType + '\'' +
                ", VolOfDividendforReinvestment='" + VolOfDividendforReinvestment + '\'' +
                ", DividentDate='" + DividentDate + '\'' +
                ", DividendAmount='" + DividendAmount + '\'' +
                ", XRDate='" + XRDate + '\'' +
                ", ConfirmedAmount='" + ConfirmedAmount + '\'' +
                ", FundCode='" + FundCode + '\'' +
                ", CodeOfTargetFund='" + CodeOfTargetFund + '\'' +
                ", RegistrationDate='" + RegistrationDate + '\'' +
                ", ReturnCode='" + ReturnCode + '\'' +
                ", TransactionAccountID='" + TransactionAccountID + '\'' +
                ", DistributorCode='" + DistributorCode + '\'' +
                ", BusinessCode='" + BusinessCode + '\'' +
                ", TAAccountID='" + TAAccountID + '\'' +
                ", DepositAcct='" + DepositAcct + '\'' +
                ", DividendPerUnit='" + DividendPerUnit + '\'' +
                ", DefDividendMethod='" + DefDividendMethod + '\'' +
                ", DividendType='" + DividendType + '\'' +
                ", OtherFee2='" + OtherFee2 + '\'' +
                '}';
    }
}
