package com.jinhui.scheduler.domain.cmbfae.template;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class ProductContentVo {

	@JSONField(ordinal = 1)
	private String productCode;

	@JSONField(ordinal = 2)
	private String coProductCode;

	@JSONField(ordinal = 3)
	private String parentProductCode;

	@JSONField(ordinal = 4)
	private String productName;

	@JSONField(ordinal = 5)
	private String productSumLimit;

	@JSONField(ordinal = 6)
	private String distributeSumLimit;

	@JSONField(ordinal = 7)
	private String productSumVolume;

	@JSONField(ordinal = 8)
	private String distributeSumVol;

	@JSONField(ordinal = 9)
	private String expectedYield;

	@JSONField(ordinal = 10, format = "yyyyMMdd")
	private Date establishDate;

	@JSONField(ordinal = 11, format = "yyyyMMddhhmmss")
	private Date ipoBeginTime;

	@JSONField(ordinal = 12)
	private String purchaseBeginAmount;

	@JSONField(ordinal = 13)
	private String purchaseScopeAmount;

	@JSONField(ordinal = 14)
	private String profitCalcType;

	@JSONField(ordinal = 15)
	private String calcInterestMethod;

	@JSONField(ordinal = 16)
	private String isNeedRisk;

	@JSONField(ordinal = 17)
	private String riskLevel;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCoProductCode() {
		return coProductCode;
	}

	public void setCoProductCode(String coProductCode) {
		this.coProductCode = coProductCode;
	}

	public String getParentProductCode() {
		return parentProductCode;
	}

	public void setParentProductCode(String parentProductCode) {
		this.parentProductCode = parentProductCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSumLimit() {
		return productSumLimit;
	}

	public void setProductSumLimit(String productSumLimit) {
		this.productSumLimit = productSumLimit;
	}

	public String getDistributeSumLimit() {
		return distributeSumLimit;
	}

	public void setDistributeSumLimit(String distributeSumLimit) {
		this.distributeSumLimit = distributeSumLimit;
	}

	public String getProductSumVolume() {
		return productSumVolume;
	}

	public void setProductSumVolume(String productSumVolume) {
		this.productSumVolume = productSumVolume;
	}

	public String getDistributeSumVol() {
		return distributeSumVol;
	}

	public void setDistributeSumVol(String distributeSumVol) {
		this.distributeSumVol = distributeSumVol;
	}

	public String getExpectedYield() {
		return expectedYield;
	}

	public void setExpectedYield(String expectedYield) {
		this.expectedYield = expectedYield;
	}

	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}

	public Date getIpoBeginTime() {
		return ipoBeginTime;
	}

	public void setIpoBeginTime(Date ipoBeginTime) {
		this.ipoBeginTime = ipoBeginTime;
	}

	public String getPurchaseBeginAmount() {
		return purchaseBeginAmount;
	}

	public void setPurchaseBeginAmount(String purchaseBeginAmount) {
		this.purchaseBeginAmount = purchaseBeginAmount;
	}

	public String getPurchaseScopeAmount() {
		return purchaseScopeAmount;
	}

	public void setPurchaseScopeAmount(String purchaseScopeAmount) {
		this.purchaseScopeAmount = purchaseScopeAmount;
	}

	public String getProfitCalcType() {
		return profitCalcType;
	}

	public void setProfitCalcType(String profitCalcType) {
		this.profitCalcType = profitCalcType;
	}

	public String getCalcInterestMethod() {
		return calcInterestMethod;
	}

	public void setCalcInterestMethod(String calcInterestMethod) {
		this.calcInterestMethod = calcInterestMethod;
	}

	public String getIsNeedRisk() {
		return isNeedRisk;
	}

	public void setIsNeedRisk(String isNeedRisk) {
		this.isNeedRisk = isNeedRisk;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	@Override
	public String toString() {
		return "ProductContentVo [productCode=" + productCode
				+ ", coProductCode=" + coProductCode + ", parentProductCode="
				+ parentProductCode + ", productName=" + productName
				+ ", productSumLimit=" + productSumLimit
				+ ", distributeSumLimit=" + distributeSumLimit
				+ ", productSumVolume=" + productSumVolume
				+ ", distributeSumVol=" + distributeSumVol + ", expectedYield="
				+ expectedYield + ", establishDate=" + establishDate
				+ ", ipoBeginTime=" + ipoBeginTime + ", purchaseBeginAmount="
				+ purchaseBeginAmount + ", purchaseScopeAmount="
				+ purchaseScopeAmount + ", profitCalcType=" + profitCalcType
				+ ", calcInterestMethod=" + calcInterestMethod
				+ ", isNeedRisk=" + isNeedRisk + ", riskLevel=" + riskLevel
				+ "]";
	} 



}
