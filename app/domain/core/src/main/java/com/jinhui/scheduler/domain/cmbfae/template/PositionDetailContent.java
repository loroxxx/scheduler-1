package com.jinhui.scheduler.domain.cmbfae.template;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class PositionDetailContent {

	private String productCode;
	
	private String coProductCode;
	
	private String productName;
	
	private String coInvestUserId;
	
	private String certificateNo;
	
	private String mobilePhoneNo;
	
	private String investorName;
	
	private BigDecimal totalVolumn;
	
	private BigDecimal perVolumn;
	
	private BigDecimal totalBalance;
	
	private BigDecimal totalProfit;
	
	@JSONField(format = "yyyyMMdd")
	private Date summarizeDate;
	
	
	private String certificateType;
	
	//该字段不序列化
	@JSONField(serialize=false)
	private String userFlag;
	
	
	
	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCoInvestUserId() {
		return coInvestUserId;
	}

	public void setCoInvestUserId(String coInvestUserId) {
		this.coInvestUserId = coInvestUserId;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}

	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public BigDecimal getTotalVolumn() {
		return totalVolumn;
	}

	public void setTotalVolumn(BigDecimal totalVolumn) {
		this.totalVolumn = totalVolumn;
	}

	public BigDecimal getPerVolumn() {
		return perVolumn;
	}

	public void setPerVolumn(BigDecimal perVolumn) {
		this.perVolumn = perVolumn;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Date getSummarizeDate() {
		return summarizeDate;
	}

	public void setSummarizeDate(Date summarizeDate) {
		this.summarizeDate = summarizeDate;
	}

	@Override
	public String toString() {
		return "PositionDetailContent [productCode=" + productCode
				+ ", coProductCode=" + coProductCode + ", productName="
				+ productName + ", coInvestUserId=" + coInvestUserId
				+ ", certificateNo=" + certificateNo + ", mobilePhoneNo="
				+ mobilePhoneNo + ", investorName=" + investorName
				+ ", totalVolumn=" + totalVolumn + ", perVolumn=" + perVolumn
				+ ", totalBalance=" + totalBalance + ", totalProfit="
				+ totalProfit + ", summarizeDate=" + summarizeDate
				+ ", certificateType=" + certificateType + "]";
	}



	
}
