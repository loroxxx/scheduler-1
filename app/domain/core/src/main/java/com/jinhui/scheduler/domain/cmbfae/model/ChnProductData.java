package com.jinhui.scheduler.domain.cmbfae.model;

import java.math.BigDecimal;
import java.util.List;

public class ChnProductData {

	private String exchangeCode;
	
	private BigDecimal currentProductLimit;
	
	private String chnCode;
	
	private BigDecimal totalLimit;
	
	private List<ChnProduct> list;

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}



	public BigDecimal getCurrentProductLimit() {
		return currentProductLimit;
	}

	public void setCurrentProductLimit(BigDecimal currentProductLimit) {
		this.currentProductLimit = currentProductLimit;
	}

	public BigDecimal getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(BigDecimal totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getChnCode() {
		return chnCode;
	}

	public void setChnCode(String chnCode) {
		this.chnCode = chnCode;
	}



	public List<ChnProduct> getList() {
		return list;
	}

	public void setList(List<ChnProduct> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ChnProductDate [exchangeCode=" + exchangeCode + ", currentProductLimit=" + currentProductLimit
				+ ", chnCode=" + chnCode + ", totalLimit=" + totalLimit + ", list=" + list + "]";
	}
	
	
}
