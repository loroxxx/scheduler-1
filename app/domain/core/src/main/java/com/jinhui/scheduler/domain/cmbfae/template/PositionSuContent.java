package com.jinhui.scheduler.domain.cmbfae.template;




public class PositionSuContent {

	private String productCode;
	
	private String coProductCode;
	
	private String productName;
	
	private long sumRecord;

	



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





	public long getSumRecord() {
		return sumRecord;
	}





	public void setSumRecord(long sumRecord) {
		this.sumRecord = sumRecord;
	}





	@Override
	public String toString() {
		return "PositionSuContent [productCode=" + productCode
				+ ", coProductCode=" + coProductCode + ", productName="
				+ productName + ", sumRecord=" + sumRecord + "]";
	}






	
	
}
