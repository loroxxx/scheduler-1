package com.jinhui.scheduler.domain.cmbfae.template;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class TransDetailContent {

	private String transactionOrderNo;
	
	private String productCode;

	private String productName;
	
	private String coProductCode;
	
	private String coInvestUserId;
	
	private String customerType;
	
	private String certificateNo;
	
	private String certificateType;
	
	private String mobilePhoneNo;
	
	private String investorName;
	
	private String transactionBankAccount;
	
	private String transactionBankName;
	
	private String transactionType;
	
	private BigDecimal transactionAmount;
	
	private BigDecimal transactionVolume;
	
	
	@JSONField( format = "yyyyMMddhhmmss")
	private Date transactionTime;

	public String getTransactionOrderNo() {
		return transactionOrderNo;
	}

	public void setTransactionOrderNo(String transactionOrderNo) {
		this.transactionOrderNo = transactionOrderNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCoProductCode() {
		return coProductCode;
	}

	public void setCoProductCode(String coProductCode) {
		this.coProductCode = coProductCode;
	}

	public String getCoInvestUserId() {
		return coInvestUserId;
	}

	public void setCoInvestUserId(String coInvestUserId) {
		this.coInvestUserId = coInvestUserId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
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

	public String getTransactionBankAccount() {
		return transactionBankAccount;
	}

	public void setTransactionBankAccount(String transactionBankAccount) {
		this.transactionBankAccount = transactionBankAccount;
	}

	public String getTransactionBankName() {
		return transactionBankName;
	}

	public void setTransactionBankName(String transactionBankName) {
		this.transactionBankName = transactionBankName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public BigDecimal getTransactionVolume() {
		return transactionVolume;
	}

	public void setTransactionVolume(BigDecimal transactionVolume) {
		this.transactionVolume = transactionVolume;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		return "TransDetailContent [transactionOrderNo=" + transactionOrderNo
				+ ", productCode=" + productCode + ", productName="
				+ productName + ", coProductCode=" + coProductCode
				+ ", coInvestUserId=" + coInvestUserId + ", customerType="
				+ customerType + ", certificateNo=" + certificateNo
				+ ", certificateType=" + certificateType + ", mobilePhoneNo="
				+ mobilePhoneNo + ", investorName=" + investorName
				+ ", transactionBankAccount=" + transactionBankAccount
				+ ", transactionBankName=" + transactionBankName
				+ ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount
				+ ", transactionVolume=" + transactionVolume
				+ ", transactionTime=" + transactionTime + "]";
	}


	
	
}
