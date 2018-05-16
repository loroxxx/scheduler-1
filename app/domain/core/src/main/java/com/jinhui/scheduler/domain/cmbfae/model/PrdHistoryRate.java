package com.jinhui.scheduler.domain.cmbfae.model;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 产品历史利率表
 * 
 * @author luoyuanq
 * @email 184673258@qq.com
 * @date 2017-05-24 17:29:07
 */
public class PrdHistoryRate  {



	//产品名称
	private String productName;
	

	//金飞镖产品代码
	private String ProductNo;
	

	//产品类型:活期,定期
	private String productType;
	

	//产品收益率
	private BigDecimal incomeRate;
	

	//万元收益
	private BigDecimal percentIncomeRate;
	

	//更新日期
	private Date updateDate;
	

	//交易所
	private String exchangeCode;
	


	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}



	public String getProductNo() {
		return ProductNo;
	}

	public void setProductNo(String productNo) {
		ProductNo = productNo;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductType() {
		return productType;
	}



	public BigDecimal getIncomeRate() {
		return incomeRate;
	}

	public void setIncomeRate(BigDecimal incomeRate) {
		this.incomeRate = incomeRate;
	}

	public void setPercentIncomeRate(BigDecimal percentIncomeRate) {
		this.percentIncomeRate = percentIncomeRate;
	}

	public BigDecimal getPercentIncomeRate() {
		return percentIncomeRate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}
}
