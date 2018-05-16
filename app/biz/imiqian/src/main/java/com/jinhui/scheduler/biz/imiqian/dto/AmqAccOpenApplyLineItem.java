package com.jinhui.scheduler.biz.imiqian.dto;

/**
 * 开户申请文件读取的映射对象
 *
 *  wsc
 *  2017-05-19 14:20
 **/
public class AmqAccOpenApplyLineItem {
    //通讯地址
    private String address ;
    //法人代表身份证件代码
    private String instReprIDCode;
    //法人代表证件类型
    private String instReprIDType;
    //法人代表姓名
    private String instReprName;
    //申请单编号
    private String appSheetSerialNo;
    //个人证件类型及机构证件型
    private String certificateType ;
    //投资人证件号码
    private String certificateNo;
    //投资人户名
    private String investorName;
    //交易发生日期
    private String transactionDate;
    //交易发生时间
    private String transactionTime;
    //个人/机构标志
    private String individualOrInstitution;
    //投资人基金交易帐号
    private String transactionAccountID;
    //销售人代码
    private String distributorCode;
    //业务代码
    private String businessCode;
    //网点号码
    private String branchCode;
    //投资人邮政编码
    private String postCode;
    //投资人学历
    private String educationLevel;
    //投资人E-MAIL地址
    private String emailAddress;
    //投资人传真号码
    private String faxNo;
    //投资人职业代码
    private String vocationCode;
    //投资人年收入
    private String annualIncome;
    //投资人手机号码
    private String mobileTelNo ;
    //投资人性别
    private String sex;
    //投资人收款银行账户账号
    private String acctNoOfInvestorInClearingAgency;
    //投资人收款银行账户开户行
    private String clearingAgency;
    //对帐单寄送方式
    private String deliverWay;
    //机构法人身份证件有效日期
    private String instReprCertValidDate;
    //客户风险等级
    private String clientRiskRate;
    //机构法人经营范围
    private String instReprManageRange;
    //控股股东
    private String controlHolder;
    //行业
    private String vocation;
    //企业性质
    private String corpoProperty;
    //员工人数
    private String staffNum;
    //省/直辖市
    private String province;
    //市
    private String city;
    //县/区
    private String country;
    //截断字符串1
    private String otherStrOne;
    //截断字符串2
    private String otherStrTwo;
    //截断字符串3
    private String otherStrThree;
    //其他
    private String other;
    //证联客户平台号
    private String zlUserId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstReprIDCode() {
        return instReprIDCode;
    }

    public void setInstReprIDCode(String instReprIDCode) {
        this.instReprIDCode = instReprIDCode;
    }

    public String getInstReprIDType() {
        return instReprIDType;
    }

    public void setInstReprIDType(String instReprIDType) {
        this.instReprIDType = instReprIDType;
    }

    public String getInstReprName() {
        return instReprName;
    }

    public void setInstReprName(String instReprName) {
        this.instReprName = instReprName;
    }

    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
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

    public String getIndividualOrInstitution() {
        return individualOrInstitution;
    }

    public void setIndividualOrInstitution(String individualOrInstitution) {
        this.individualOrInstitution = individualOrInstitution;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getVocationCode() {
        return vocationCode;
    }

    public void setVocationCode(String vocationCode) {
        this.vocationCode = vocationCode;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getMobileTelNo() {
        return mobileTelNo;
    }

    public void setMobileTelNo(String mobileTelNo) {
        this.mobileTelNo = mobileTelNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAcctNoOfInvestorInClearingAgency() {
        return acctNoOfInvestorInClearingAgency;
    }

    public void setAcctNoOfInvestorInClearingAgency(String acctNoOfInvestorInClearingAgency) {
        this.acctNoOfInvestorInClearingAgency = acctNoOfInvestorInClearingAgency;
    }

    public String getClearingAgency() {
        return clearingAgency;
    }

    public void setClearingAgency(String clearingAgency) {
        this.clearingAgency = clearingAgency;
    }

    public String getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay;
    }

    public String getInstReprCertValidDate() {
        return instReprCertValidDate;
    }

    public void setInstReprCertValidDate(String instReprCertValidDate) {
        this.instReprCertValidDate = instReprCertValidDate;
    }

    public String getClientRiskRate() {
        return clientRiskRate;
    }

    public void setClientRiskRate(String clientRiskRate) {
        this.clientRiskRate = clientRiskRate;
    }

    public String getInstReprManageRange() {
        return instReprManageRange;
    }

    public void setInstReprManageRange(String instReprManageRange) {
        this.instReprManageRange = instReprManageRange;
    }

    public String getControlHolder() {
        return controlHolder;
    }

    public void setControlHolder(String controlHolder) {
        this.controlHolder = controlHolder;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getCorpoProperty() {
        return corpoProperty;
    }

    public void setCorpoProperty(String corpoProperty) {
        this.corpoProperty = corpoProperty;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOtherStrOne() {
        return otherStrOne;
    }

    public void setOtherStrOne(String otherStrOne) {
        this.otherStrOne = otherStrOne;
    }

    public String getOtherStrTwo() {
        return otherStrTwo;
    }

    public void setOtherStrTwo(String otherStrTwo) {
        this.otherStrTwo = otherStrTwo;
    }

    public String getOtherStrThree() {
        return otherStrThree;
    }

    public void setOtherStrThree(String otherStrThree) {
        this.otherStrThree = otherStrThree;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getZlUserId() {
        return zlUserId;
    }

    public void setZlUserId(String zlUserId) {
        this.zlUserId = zlUserId;
    }

    @Override
    public String toString() {
        return "AmqAccOpenApplyLineItem{" +
                "address='" + address + '\'' +
                ", instReprIDCode='" + instReprIDCode + '\'' +
                ", instReprIDType='" + instReprIDType + '\'' +
                ", instReprName='" + instReprName + '\'' +
                ", appSheetSerialNo='" + appSheetSerialNo + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateNo='" + certificateNo + '\'' +
                ", investorName='" + investorName + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", individualOrInstitution='" + individualOrInstitution + '\'' +
                ", transactionAccountID='" + transactionAccountID + '\'' +
                ", distributorCode='" + distributorCode + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", postCode='" + postCode + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", faxNo='" + faxNo + '\'' +
                ", vocationCode='" + vocationCode + '\'' +
                ", annualIncome='" + annualIncome + '\'' +
                ", mobileTelNo='" + mobileTelNo + '\'' +
                ", sex='" + sex + '\'' +
                ", acctNoOfInvestorInClearingAgency='" + acctNoOfInvestorInClearingAgency + '\'' +
                ", clearingAgency='" + clearingAgency + '\'' +
                ", deliverWay='" + deliverWay + '\'' +
                ", instReprCertValidDate='" + instReprCertValidDate + '\'' +
                ", clientRiskRate='" + clientRiskRate + '\'' +
                ", instReprManageRange='" + instReprManageRange + '\'' +
                ", controlHolder='" + controlHolder + '\'' +
                ", vocation='" + vocation + '\'' +
                ", corpoProperty='" + corpoProperty + '\'' +
                ", staffNum='" + staffNum + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", otherStrOne='" + otherStrOne + '\'' +
                ", otherStrTwo='" + otherStrTwo + '\'' +
                ", otherStrThree='" + otherStrThree + '\'' +
                ", other='" + other + '\'' +
                ", zlUserId='" + zlUserId + '\'' +
                '}';
    }
}
