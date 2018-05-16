package com.jinhui.scheduler.domain.cmbfae.model;

import java.math.BigDecimal;
import java.util.Date;

public class ChnProduct {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 金飞镖产品代码
     */
    private String productNo;

    /**
     * 收益率
     */
    private BigDecimal incomeRate;

    /**
     * 万元收益
     */
    private BigDecimal percentIncomeRate;

    /**
     * 名义上的产品收益率
     */
    private BigDecimal nominalIncomeRate;

    /**
     * 名义上的万元收益
     */
    private BigDecimal nominalPercentIncomeRate;

    /**
     * 万元收益的差额（名义上的万元收益减去实际的万元收益）
     */
    private BigDecimal incomeDifference;

    /**
     * 客户当日申购金额上限
     */
    private BigDecimal subsToplimit;

    /**
     * 客户当日赎回金额上限
     */
    private BigDecimal redeemToplimit;

    /**
     * 客户持仓上限
     */
    private BigDecimal positionLimit;

    /**
     * 产品当日申购金额上限
     */
    private BigDecimal productSubsToplimit;

    /**
     * 产品当日赎回金额上限
     */
    private BigDecimal productRedeemToplimit;

    /**
     * 产品总额度
     */
    private BigDecimal totalLimit;

    /**
     * 渠道产品分配总额度
     */
    private BigDecimal assignLimit;

    /**
     * 利率录入日期
     */
    private Date preferDate;

    /**
     * 渠道代码，渠道在平台中的代码标识
     */
    private String chnCode;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取金飞镖产品代码
     *
     * @return product_no - 金飞镖产品代码
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置金飞镖产品代码
     *
     * @param productNo 金飞镖产品代码
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * 获取收益率
     *
     * @return income_rate - 收益率
     */
    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    /**
     * 设置收益率
     *
     * @param incomeRate 收益率
     */
    public void setIncomeRate(BigDecimal incomeRate) {
        this.incomeRate = incomeRate;
    }

    /**
     * 获取万元收益
     *
     * @return percent_income_rate - 万元收益
     */
    public BigDecimal getPercentIncomeRate() {
        return percentIncomeRate;
    }

    /**
     * 设置万元收益
     *
     * @param percentIncomeRate 万元收益
     */
    public void setPercentIncomeRate(BigDecimal percentIncomeRate) {
        this.percentIncomeRate = percentIncomeRate;
    }

    /**
     * 获取名义上的产品收益率
     *
     * @return nominal_income_rate - 名义上的产品收益率
     */
    public BigDecimal getNominalIncomeRate() {
        return nominalIncomeRate;
    }

    /**
     * 设置名义上的产品收益率
     *
     * @param nominalIncomeRate 名义上的产品收益率
     */
    public void setNominalIncomeRate(BigDecimal nominalIncomeRate) {
        this.nominalIncomeRate = nominalIncomeRate;
    }

    /**
     * 获取名义上的万元收益
     *
     * @return nominal_percent_income_rate - 名义上的万元收益
     */
    public BigDecimal getNominalPercentIncomeRate() {
        return nominalPercentIncomeRate;
    }

    /**
     * 设置名义上的万元收益
     *
     * @param nominalPercentIncomeRate 名义上的万元收益
     */
    public void setNominalPercentIncomeRate(BigDecimal nominalPercentIncomeRate) {
        this.nominalPercentIncomeRate = nominalPercentIncomeRate;
    }

    /**
     * 获取万元收益的差额（名义上的万元收益减去实际的万元收益）
     *
     * @return income_difference - 万元收益的差额（名义上的万元收益减去实际的万元收益）
     */
    public BigDecimal getIncomeDifference() {
        return incomeDifference;
    }

    /**
     * 设置万元收益的差额（名义上的万元收益减去实际的万元收益）
     *
     * @param incomeDifference 万元收益的差额（名义上的万元收益减去实际的万元收益）
     */
    public void setIncomeDifference(BigDecimal incomeDifference) {
        this.incomeDifference = incomeDifference;
    }

    /**
     * 获取客户当日申购金额上限
     *
     * @return subs_toplimit - 客户当日申购金额上限
     */
    public BigDecimal getSubsToplimit() {
        return subsToplimit;
    }

    /**
     * 设置客户当日申购金额上限
     *
     * @param subsToplimit 客户当日申购金额上限
     */
    public void setSubsToplimit(BigDecimal subsToplimit) {
        this.subsToplimit = subsToplimit;
    }

    /**
     * 获取客户当日赎回金额上限
     *
     * @return redeem_toplimit - 客户当日赎回金额上限
     */
    public BigDecimal getRedeemToplimit() {
        return redeemToplimit;
    }

    /**
     * 设置客户当日赎回金额上限
     *
     * @param redeemToplimit 客户当日赎回金额上限
     */
    public void setRedeemToplimit(BigDecimal redeemToplimit) {
        this.redeemToplimit = redeemToplimit;
    }

    /**
     * 获取客户持仓上限
     *
     * @return position_limit - 客户持仓上限
     */
    public BigDecimal getPositionLimit() {
        return positionLimit;
    }

    /**
     * 设置客户持仓上限
     *
     * @param positionLimit 客户持仓上限
     */
    public void setPositionLimit(BigDecimal positionLimit) {
        this.positionLimit = positionLimit;
    }

    /**
     * 获取产品当日申购金额上限
     *
     * @return product_subs_toplimit - 产品当日申购金额上限
     */
    public BigDecimal getProductSubsToplimit() {
        return productSubsToplimit;
    }

    /**
     * 设置产品当日申购金额上限
     *
     * @param productSubsToplimit 产品当日申购金额上限
     */
    public void setProductSubsToplimit(BigDecimal productSubsToplimit) {
        this.productSubsToplimit = productSubsToplimit;
    }

    /**
     * 获取产品当日赎回金额上限
     *
     * @return product_redeem_toplimit - 产品当日赎回金额上限
     */
    public BigDecimal getProductRedeemToplimit() {
        return productRedeemToplimit;
    }

    /**
     * 设置产品当日赎回金额上限
     *
     * @param productRedeemToplimit 产品当日赎回金额上限
     */
    public void setProductRedeemToplimit(BigDecimal productRedeemToplimit) {
        this.productRedeemToplimit = productRedeemToplimit;
    }

    /**
     * 获取产品总额度
     *
     * @return total_limit - 产品总额度
     */
    public BigDecimal getTotalLimit() {
        return totalLimit;
    }

    /**
     * 设置产品总额度
     *
     * @param totalLimit 产品总额度
     */
    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }

    /**
     * 获取渠道产品分配总额度
     *
     * @return assign_limit - 渠道产品分配总额度
     */
    public BigDecimal getAssignLimit() {
        return assignLimit;
    }

    /**
     * 设置渠道产品分配总额度
     *
     * @param assignLimit 渠道产品分配总额度
     */
    public void setAssignLimit(BigDecimal assignLimit) {
        this.assignLimit = assignLimit;
    }

    /**
     * 获取利率录入日期
     *
     * @return prefer_date - 利率录入日期
     */
    public Date getPreferDate() {
        return preferDate;
    }

    /**
     * 设置利率录入日期
     *
     * @param preferDate 利率录入日期
     */
    public void setPreferDate(Date preferDate) {
        this.preferDate = preferDate;
    }

    /**
     * 获取渠道代码，渠道在平台中的代码标识
     *
     * @return chn_code - 渠道代码，渠道在平台中的代码标识
     */
    public String getChnCode() {
        return chnCode;
    }

    /**
     * 设置渠道代码，渠道在平台中的代码标识
     *
     * @param chnCode 渠道代码，渠道在平台中的代码标识
     */
    public void setChnCode(String chnCode) {
        this.chnCode = chnCode == null ? null : chnCode.trim();
    }
}