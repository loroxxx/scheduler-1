package com.jinhui.scheduler.domain.cmbfae.model;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 投资人持仓表
 * 
 * @author luoyuanq
 * @email 184673258@qq.com
 * @date 2017-05-25 11:21:02
 */
public class InvestorPosition  {


	//汇总日期
	private Date gatherDate;
	

	//投资人id,导入渠道数据后由平台生成
	private String investorId;
	

	//渠道用户id
	private String chnId;
	

	//渠道代码(渠道在平台中的代码标识)
	private String chnCode;
	

	//投资人姓名
	private String name;
	

	//金飞镖产品代码
	private String productNo;
	

	//产品名称
	private String productName;
	

	//持有份额
	private BigDecimal totalPostionVol;
	

	//产品每份金额
	private BigDecimal productVol;
	

	//持有金额
	private BigDecimal totalPostionAmount;
	

	//总收益
	private BigDecimal totalIncome;
	

	//总赎回金额
	private BigDecimal totalRedemedAmount;
	

	//总申购金额
	private BigDecimal totalSubsAmount;
	

	//滚存标志：0-关闭，1-开启
	private String rollingFlag;
	

	//滚存份额
	private BigDecimal rollingVol;
	


	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}

	public Date getGatherDate() {
		return gatherDate;
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

	public void setTotalPostionVol(BigDecimal totalPostionVol) {
		this.totalPostionVol = totalPostionVol;
	}

	public BigDecimal getTotalPostionVol() {
		return totalPostionVol;
	}

	public void setProductVol(BigDecimal productVol) {
		this.productVol = productVol;
	}

	public BigDecimal getProductVol() {
		return productVol;
	}

	public void setTotalPostionAmount(BigDecimal totalPostionAmount) {
		this.totalPostionAmount = totalPostionAmount;
	}

	public BigDecimal getTotalPostionAmount() {
		return totalPostionAmount;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalRedemedAmount(BigDecimal totalRedemedAmount) {
		this.totalRedemedAmount = totalRedemedAmount;
	}

	public BigDecimal getTotalRedemedAmount() {
		return totalRedemedAmount;
	}

	public void setTotalSubsAmount(BigDecimal totalSubsAmount) {
		this.totalSubsAmount = totalSubsAmount;
	}

	public BigDecimal getTotalSubsAmount() {
		return totalSubsAmount;
	}

	public void setRollingFlag(String rollingFlag) {
		this.rollingFlag = rollingFlag;
	}

	public String getRollingFlag() {
		return rollingFlag;
	}

	public void setRollingVol(BigDecimal rollingVol) {
		this.rollingVol = rollingVol;
	}

	public BigDecimal getRollingVol() {
		return rollingVol;
	}
}
