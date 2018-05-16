package com.jinhui.scheduler.domain.cmbfae.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 收益表
 * 
 * @author luoyuanq
 * @email 184673258@qq.com
 * @date 2017-05-27 09:36:35
 */
public class InvestorIncome  {


	//金飞镖业务流水号
	private String serialNumber;
	

	//收益计提日期
	private Date applyDate;
	

	//收益确认日期
	private Date confirmDate;
	

	//收益处理状态：0000-成功，0010-失败
	private String incomeState;
	

	//汇总日期
	private Date gatherDate;
	

	//批次号
	private String batchCode;
	

	//投资人Id,导入渠道数据后由平台生成
	private String investorId;
	

	//渠道用户id
	private String chnId;
	

	//渠道代码，渠道在平台中的代码标识
	private String chnCode;
	

	//投资人姓名
	private String name;
	

	//金飞镖产品代码
	private String productNo;
	

	//产品名称
	private String productName;
	

	//持有金额
	private BigDecimal totalPostionAmount;
	

	//收益金额：定期产品，为固定期限后的收益；活期产品为每日收益
	private BigDecimal incomeAmount;
	

	//收益率
	private BigDecimal incomeRate;
	

	//万元收益
	private BigDecimal percentIncome;
	

	//创建时间
	private Date createTime;
	

	//更新时间
	private Date updateTime;
	


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setIncomeState(String incomeState) {
		this.incomeState = incomeState;
	}

	public String getIncomeState() {
		return incomeState;
	}

	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}

	public Date getGatherDate() {
		return gatherDate;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setChnId(String chnId) {
		this.chnId = chnId;
	}

	public String getChnId() {
		return chnId;
	}

	public void setChnCode(String chnCode) {
		this.chnCode = chnCode;
	}

	public String getChnCode() {
		return chnCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setTotalPostionAmount(BigDecimal totalPostionAmount) {
		this.totalPostionAmount = totalPostionAmount;
	}

	public BigDecimal getTotalPostionAmount() {
		return totalPostionAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeRate(BigDecimal incomeRate) {
		this.incomeRate = incomeRate;
	}

	public BigDecimal getIncomeRate() {
		return incomeRate;
	}

	public void setPercentIncome(BigDecimal percentIncome) {
		this.percentIncome = percentIncome;
	}

	public BigDecimal getPercentIncome() {
		return percentIncome;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
}
