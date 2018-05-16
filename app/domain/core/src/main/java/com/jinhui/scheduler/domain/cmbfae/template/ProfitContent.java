package com.jinhui.scheduler.domain.cmbfae.template;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ProfitContent {

	
	private String productCode;
	
	
	private String coProductCode;
	
	
	private String productName;
	
	
	private BigDecimal profitValue;
	
	
	private String profitType;
	
	@JSONField(format = "yyyyMMdd")
	private Date profitDate;

	
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

	public BigDecimal getProfitValue() {
		return profitValue;
	}

	public void setProfitValue(BigDecimal profitValue) {
		this.profitValue = profitValue;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public Date getProfitDate() {
		return profitDate;
	}

	public void setProfitDate(Date profitDate) {
		this.profitDate = profitDate;
	}

	@Override
	public String toString() {
		return "ProfitContont [productCode=" + productCode + ", coProductCode="
				+ coProductCode + ", productName=" + productName
				+ ", profitValue=" + profitValue + ", profitType=" + profitType
				+ ", profitDate=" + profitDate + "]";
	}


	
	
}
