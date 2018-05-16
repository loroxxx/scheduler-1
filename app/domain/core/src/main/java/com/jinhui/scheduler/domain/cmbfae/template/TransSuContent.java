package com.jinhui.scheduler.domain.cmbfae.template;

import java.math.BigDecimal;



public class TransSuContent {
	
	
	private String productCode;
	
	
	private String coProductCode;
	
	
	private String productName;
	
	
	private BigDecimal purchaseAmount;
	
	
	private long purchaseRecord;
	
	
	private BigDecimal paybackAmount;
	
	
	private long paybackRecord;
	
	
	private long sumRecord;

	
	
	

	public TransSuContent() {
		BigDecimal initNum=new BigDecimal(0);
		this.purchaseAmount = initNum;
		this.purchaseRecord = 0;
		this.paybackAmount = initNum;
		this.paybackRecord = 0;
		this.sumRecord = 0;
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





	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}





	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}





	public long getPurchaseRecord() {
		return purchaseRecord;
	}





	public void setPurchaseRecord(long purchaseRecord) {
		this.purchaseRecord = purchaseRecord;
	}





	public BigDecimal getPaybackAmount() {
		return paybackAmount;
	}





	public void setPaybackAmount(BigDecimal paybackAmount) {
		this.paybackAmount = paybackAmount;
	}





	public long getPaybackRecord() {
		return paybackRecord;
	}





	public void setPaybackRecord(long paybackRecord) {
		this.paybackRecord = paybackRecord;
	}





	public long getSumRecord() {
		return sumRecord;
	}





	public void setSumRecord(long sumRecord) {
		this.sumRecord = sumRecord;
	}





	@Override
	public String toString() {
		return "TransSuContent [productCode=" + productCode
				+ ", coProductCode=" + coProductCode + ", productName="
				+ productName + ", purchaseAmount=" + purchaseAmount
				+ ", purchaseRecord=" + purchaseRecord + ", paybackAmount="
				+ paybackAmount + ", paybackRecord=" + paybackRecord
				+ ", sumRecord=" + sumRecord + "]";
	}


	




	
	
}
