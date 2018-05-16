package com.jinhui.scheduler.domain.core;

import java.math.BigDecimal;
import java.util.Date;

public class TermPaymentSchedule {
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
     * 金交所产品代码
     */
    private String platProductNo;

    /**
     * 产品总金额
     */
    private BigDecimal totalAmount;

    /**
     * 产品总期数
     */
    private Integer productTotalPeriods;

    /**
     * 总付息金额(只包含利息)
     */
    private BigDecimal interestAmount;

    /**
     * 付息期数
     */
    private Integer interestPeriod;

    /**
     * 每期天数
     */
    private Integer interestDays;

    /**
     * 单份付息金额(只包含利息)
     */
    private BigDecimal perInterestAmount;

    /**
     * 付息状态
     */
    private String interestState;

    /**
     * 付息时间
     */
    private Date interestDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date gmtModified;

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
     * 获取金交所产品代码
     *
     * @return plat_product_no - 金交所产品代码
     */
    public String getPlatProductNo() {
        return platProductNo;
    }

    /**
     * 设置金交所产品代码
     *
     * @param platProductNo 金交所产品代码
     */
    public void setPlatProductNo(String platProductNo) {
        this.platProductNo = platProductNo == null ? null : platProductNo.trim();
    }

    /**
     * 获取产品总金额
     *
     * @return total_amount - 产品总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置产品总金额
     *
     * @param totalAmount 产品总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取产品总期数
     *
     * @return product_total_periods - 产品总期数
     */
    public Integer getProductTotalPeriods() {
        return productTotalPeriods;
    }

    /**
     * 设置产品总期数
     *
     * @param productTotalPeriods 产品总期数
     */
    public void setProductTotalPeriods(Integer productTotalPeriods) {
        this.productTotalPeriods = productTotalPeriods;
    }

    /**
     * 获取总付息金额(只包含利息)
     *
     * @return interest_amount - 总付息金额(只包含利息)
     */
    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    /**
     * 设置总付息金额(只包含利息)
     *
     * @param interestAmount 总付息金额(只包含利息)
     */
    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    /**
     * 获取付息期数
     *
     * @return interest_period - 付息期数
     */
    public Integer getInterestPeriod() {
        return interestPeriod;
    }

    /**
     * 设置付息期数
     *
     * @param interestPeriod 付息期数
     */
    public void setInterestPeriod(Integer interestPeriod) {
        this.interestPeriod = interestPeriod;
    }

    /**
     * 获取每期天数
     *
     * @return interest_days - 每期天数
     */
    public Integer getInterestDays() {
        return interestDays;
    }

    /**
     * 设置每期天数
     *
     * @param interestDays 每期天数
     */
    public void setInterestDays(Integer interestDays) {
        this.interestDays = interestDays;
    }

    /**
     * 获取单份付息金额(只包含利息)
     *
     * @return per_interest_amount - 单份付息金额(只包含利息)
     */
    public BigDecimal getPerInterestAmount() {
        return perInterestAmount;
    }

    /**
     * 设置单份付息金额(只包含利息)
     *
     * @param perInterestAmount 单份付息金额(只包含利息)
     */
    public void setPerInterestAmount(BigDecimal perInterestAmount) {
        this.perInterestAmount = perInterestAmount;
    }

    /**
     * 获取付息状态
     *
     * @return interest_state - 付息状态
     */
    public String getInterestState() {
        return interestState;
    }

    /**
     * 设置付息状态
     *
     * @param interestState 付息状态
     */
    public void setInterestState(String interestState) {
        this.interestState = interestState == null ? null : interestState.trim();
    }

    /**
     * 获取付息时间
     *
     * @return interest_date - 付息时间
     */
    public Date getInterestDate() {
        return interestDate;
    }

    /**
     * 设置付息时间
     *
     * @param interestDate 付息时间
     */
    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}