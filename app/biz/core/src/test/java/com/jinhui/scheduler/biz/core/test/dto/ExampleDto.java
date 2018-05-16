package com.jinhui.scheduler.biz.core.test.dto;

import com.jinhui.scheduler.biz.core.test.processor.ExampleFileConfirmProcessor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 从数据源(json、txt、db等)通过reader读入的数据源可以通过processor
 * {@link ExampleFileConfirmProcessor}
 * 封装成Dto对象, 然后传入writer
 */
public class ExampleDto implements Serializable {
    //产品名称
    private String            productName;
    //产品简称
    private String            productShortName;
    //发行人ID
    private Long              institutionId;
    //发行人名称简称
    private String            institutionName;
    //产品编码
    private String            productCode;
    private String corpProductName;
    private String corpProductCode;
    private String parentProductCode;
    //发行规模
    private String            issuingScale;
    //是否限额。0：否，1：是。招银前海默认限额
    private Byte              isLimited = 1;
    //风评等级
    private String     riskLevel;
    //预期收益率
    private BigDecimal expectedYearIncomeRate;
    //起投金额
    private Integer    miniAmount;
    //递增金额
    private Integer    increasingAmount;
    //投资期限(数据库存成天，一个月以30天计算，一年以360天计算。如1.5月为45天。)
    private String     investmentHorizon;
    //期限单位( UNKNOWN("-1", "未知"), DAY("d001", "天"), MONTH("m002", "月"), YEAR("y003", "年"))
    private String     periodUnit;
    //起息日
    private String            interestDate;
    //计息方式(01	ACT/360 02	ACT/365 03	按月计息 99 其他)
    private String            interestKind;
    //收益类型(01:到期一次性还本付息，99:其他)
    private String            incomeKind;
    //是否需要风评。0：否，1：是
    private Byte              isNeedRisk        = 0;
    //到期日
    private String expireDate;
    //兑付日
    private String payDate;
    //募集开始时间
    private String raiseStartDate;
    //募集结束时间
    private String raiseEndDate;
    //产品总额度
    private BigDecimal        totalNum;
    //产品总份额
    private BigDecimal        totalShare;
    //单位净值
    private BigDecimal        nav;
    //起始期数
    private int beginTerm;
    //结束期数
    private int endTerm;
    //年化单位 一年360，一年365
    private int yearUnit;

    public int getYearUnit() {
        return yearUnit;
    }

    public void setYearUnit(int yearUnit) {
        this.yearUnit = yearUnit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductShortName() {
        return productShortName;
    }

    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Byte getIsLimited() {
        return isLimited;
    }

    public void setIsLimited(Byte isLimited) {
        this.isLimited = isLimited;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getExpectedYearIncomeRate() {
        return expectedYearIncomeRate;
    }

    public void setExpectedYearIncomeRate(BigDecimal expectedYearIncomeRate) {
        this.expectedYearIncomeRate = expectedYearIncomeRate;
    }

    public Integer getMiniAmount() {
        return miniAmount;
    }

    public void setMiniAmount(Integer miniAmount) {
        this.miniAmount = miniAmount;
    }

    public Integer getIncreasingAmount() {
        return increasingAmount;
    }

    public void setIncreasingAmount(Integer increasingAmount) {
        this.increasingAmount = increasingAmount;
    }

    public String getInvestmentHorizon() {
        return investmentHorizon;
    }

    public void setInvestmentHorizon(String investmentHorizon) {
        this.investmentHorizon = investmentHorizon;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public String getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(String interestDate) {
        this.interestDate = interestDate;
    }

    public String getInterestKind() {
        return interestKind;
    }

    public void setInterestKind(String interestKind) {
        this.interestKind = interestKind;
    }

    public String getIncomeKind() {
        return incomeKind;
    }

    public void setIncomeKind(String incomeKind) {
        this.incomeKind = incomeKind;
    }

    public Byte getIsNeedRisk() {
        return isNeedRisk;
    }

    public void setIsNeedRisk(Byte isNeedRisk) {
        this.isNeedRisk = isNeedRisk;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getRaiseStartDate() {
        return raiseStartDate;
    }

    public void setRaiseStartDate(String raiseStartDate) {
        this.raiseStartDate = raiseStartDate;
    }

    public String getRaiseEndDate() {
        return raiseEndDate;
    }

    public void setRaiseEndDate(String raiseEndDate) {
        this.raiseEndDate = raiseEndDate;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public String getIssuingScale() {
        return issuingScale;
    }

    public void setIssuingScale(String issuingScale) {
        this.issuingScale = issuingScale;
    }

    public BigDecimal getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(BigDecimal totalShare) {
        this.totalShare = totalShare;
    }

    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    public int getBeginTerm() {
        return beginTerm;
    }

    public void setBeginTerm(int beginTerm) {
        this.beginTerm = beginTerm;
    }

    public int getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(int endTerm) {
        this.endTerm = endTerm;
    }

    public String getCorpProductName() {
        return corpProductName;
    }

    public void setCorpProductName(String corpProductName) {
        this.corpProductName = corpProductName;
    }

    public String getCorpProductCode() {
        return corpProductCode;
    }

    public void setCorpProductCode(String corpProductCode) {
        this.corpProductCode = corpProductCode;
    }

    public String getParentProductCode() {
        return parentProductCode;
    }

    public void setParentProductCode(String parentProductCode) {
        this.parentProductCode = parentProductCode;
    }
}
