package com.jinhui.scheduler.domain.cmbfae.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品表
 * 
 * @author luoyuanq
 * @date 2017-05-19 18:41:01
 */

public class Product {

	//产品代码
	private String productNo;
	
	
	//产品名称
	private String productName;
	


	//金飞镖产品代码
	private String platProductNo;
	

	//父产品代码
	private String parentProductNo;
	

	//产品发行方
	private String productIssuer;
	

	//交易所代码
	private String exchangeNo;
	

	//产品类型:0-活期,1-定期
	private String productType;
	

	//产品期限
	private BigDecimal productExpdate;
	

	//期限单位：0-日，1-月，2-年
	private BigDecimal expdateUnit;
	

	//01-固定收益，02-固定+浮动，03-额度浮动，04-净值浮动，05-期限浮动，06-分配收益，07-约定收益 
	private String calIncomeWay;
	

	//产品计息方式:0-ACT/360,1-ACT/365,2-月
	private String calRateWay;
	

	//产品成立日，对于活期产品
	private Date setupDate;
	

	//产品起息日，对于定期产品
	private Date rateDate;
	

	//产品到期日，对于定期产品
	private Date termDate;
	

	//产品兑付日
	private Date casheDate;
	

	//名义产品收益率
	private BigDecimal nominalIncomeRate;

	//名义万元收益
	private BigDecimal nominalPercentIncomeRate;


	//产品收益率
	private BigDecimal incomeRate;

	//万元收益
	private BigDecimal percentIncomeRate;


	//募集起始时间
	private Date ipoStartDate;
	

	//募集结束时间
	private Date ipoEndDate;
	

	//起始期数
	private BigDecimal startPeriods;
	

	//结束期数
	private BigDecimal endPeriods;
	

	//产品总额度
	private BigDecimal totalLimit;
	

	//产品总份额
	private BigDecimal totalVol;
	

	//产品每份金额
	private BigDecimal productVol;
	

	//申购起始金额
	private BigDecimal subsStartAmount;
	

	//申购增减幅度
	private BigDecimal subsRange;
	

	//是否需要风险测评：1-是，0-否
	private String riskAssess;
	

	//风险级别：01-低，02-较低，03-中，04-较高，05-高
	private String riskLevel;
	
	private Date createTime;


	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setPlatProductNo(String platProductNo) {
		this.platProductNo = platProductNo;
	}

	public String getPlatProductNo() {
		return platProductNo;
	}

	public void setParentProductNo(String parentProductNo) {
		this.parentProductNo = parentProductNo;
	}

	public String getParentProductNo() {
		return parentProductNo;
	}

	public void setProductIssuer(String productIssuer) {
		this.productIssuer = productIssuer;
	}

	public String getProductIssuer() {
		return productIssuer;
	}

	public void setExchangeNo(String exchangeNo) {
		this.exchangeNo = exchangeNo;
	}

	public String getExchangeNo() {
		return exchangeNo;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductExpdate(BigDecimal productExpdate) {
		this.productExpdate = productExpdate;
	}

	public BigDecimal getProductExpdate() {
		return productExpdate;
	}

	public void setExpdateUnit(BigDecimal expdateUnit) {
		this.expdateUnit = expdateUnit;
	}

	public BigDecimal getExpdateUnit() {
		return expdateUnit;
	}

	public void setCalIncomeWay(String calIncomeWay) {
		this.calIncomeWay = calIncomeWay;
	}

	public String getCalIncomeWay() {
		return calIncomeWay;
	}

	public void setCalRateWay(String calRateWay) {
		this.calRateWay = calRateWay;
	}

	public String getCalRateWay() {
		return calRateWay;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	public Date getSetupDate() {
		return setupDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	public Date getTermDate() {
		return termDate;
	}

	public void setCasheDate(Date casheDate) {
		this.casheDate = casheDate;
	}

	public Date getCasheDate() {
		return casheDate;
	}

	public void setIncomeRate(BigDecimal incomeRate) {
		this.incomeRate = incomeRate;
	}

	
	public BigDecimal getIncomeRate() {
		return incomeRate;
	}

	public void setIpoStartDate(Date ipoStartDate) {
		this.ipoStartDate = ipoStartDate;
	}

	public Date getIpoStartDate() {
		return ipoStartDate;
	}

	public void setIpoEndDate(Date ipoEndDate) {
		this.ipoEndDate = ipoEndDate;
	}

	public Date getIpoEndDate() {
		return ipoEndDate;
	}

	public void setStartPeriods(BigDecimal startPeriods) {
		this.startPeriods = startPeriods;
	}

	public BigDecimal getStartPeriods() {
		return startPeriods;
	}

	public void setEndPeriods(BigDecimal endPeriods) {
		this.endPeriods = endPeriods;
	}

	public BigDecimal getEndPeriods() {
		return endPeriods;
	}

	public void setTotalLimit(BigDecimal totalLimit) {
		this.totalLimit = totalLimit;
	}

	public BigDecimal getTotalLimit() {
		return totalLimit;
	}

	public void setTotalVol(BigDecimal totalVol) {
		this.totalVol = totalVol;
	}

	public BigDecimal getTotalVol() {
		return totalVol;
	}

	public void setProductVol(BigDecimal productVol) {
		this.productVol = productVol;
	}

	public BigDecimal getProductVol() {
		return productVol;
	}

	public void setSubsStartAmount(BigDecimal subsStartAmount) {
		this.subsStartAmount = subsStartAmount;
	}

	public BigDecimal getSubsStartAmount() {
		return subsStartAmount;
	}

	public void setSubsRange(BigDecimal subsRange) {
		this.subsRange = subsRange;
	}

	public BigDecimal getSubsRange() {
		return subsRange;
	}

	public void setRiskAssess(String riskAssess) {
		this.riskAssess = riskAssess;
	}

	public String getRiskAssess() {
		return riskAssess;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskLevel() {
		return riskLevel;
	}


	public BigDecimal getPercentIncomeRate() {
		return percentIncomeRate;
	}

	public void setPercentIncomeRate(BigDecimal percentIncomeRate) {
		this.percentIncomeRate = percentIncomeRate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getNominalIncomeRate() {
		return nominalIncomeRate;
	}

	public void setNominalIncomeRate(BigDecimal nominalIncomeRate) {
		this.nominalIncomeRate = nominalIncomeRate;
	}

	public BigDecimal getNominalPercentIncomeRate() {
		return nominalPercentIncomeRate;
	}

	public void setNominalPercentIncomeRate(BigDecimal nominalPercentIncomeRate) {
		this.nominalPercentIncomeRate = nominalPercentIncomeRate;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName="
				+ productName + ", platProductNo=" + platProductNo
				+ ", parentProductNo=" + parentProductNo + ", productIssuer="
				+ productIssuer + ", exchangeNo=" + exchangeNo
				+ ", productType=" + productType + ", productExpdate="
				+ productExpdate + ", expdateUnit=" + expdateUnit
				+ ", calIncomeWay=" + calIncomeWay + ", calRateWay="
				+ calRateWay + ", setupDate=" + setupDate + ", rateDate="
				+ rateDate + ", termDate=" + termDate + ", casheDate="
				+ casheDate + ", incomeRate=" + incomeRate + ", ipoStartDate="
				+ ipoStartDate + ", ipoEndDate=" + ipoEndDate
				+ ", startPeriods=" + startPeriods + ", endPeriods="
				+ endPeriods + ", totalLimit=" + totalLimit + ", totalVol="
				+ totalVol + ", productVol=" + productVol
				+ ", subsStartAmount=" + subsStartAmount + ", subsRange="
				+ subsRange + ", riskAssess=" + riskAssess + ", riskLevel="
				+ riskLevel + ", createTime=" + createTime + "]";
	}

	
	
	
	
}
