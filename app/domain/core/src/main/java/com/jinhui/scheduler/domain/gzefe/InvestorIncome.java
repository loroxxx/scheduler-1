package com.jinhui.scheduler.domain.gzefe;

import java.math.BigDecimal;
import java.util.Date;

public class InvestorIncome {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 金飞镖业务流水号
     */
    private String serialNumber;

    /**
     * 收益计提日期
     */
    private Date applyDate;

    /**
     * 收益确认日期
     */
    private Date confirmDate;

    /**
     * 收益处理状态：0000-成功，0010-失败
     */
    private String incomeState;

    /**
     * 汇总日期
     */
    private Date gatherDate;

    /**
     * 批次号
     */
    private Integer batchCode;

    /**
     * 投资人Id,导入渠道数据后由平台生成
     */
    private String investorId;

    /**
     * 渠道用户id
     */
    private String chnId;

    /**
     * 渠道代码，渠道在平台中的代码标识
     */
    private String chnCode;

    /**
     * 投资人姓名
     */
    private String name;

    /**
     * 金飞镖产品代码
     */
    private String productNo;

    /**
     * 子产品代码
     */
    private String childProductNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 持有金额
     */
    private BigDecimal totalPostionAmount;

    /**
     * 收益金额：定期产品，为固定期限后的收益；活期产品为每日收益
     */
    private BigDecimal incomeAmount;

    /**
     * 收益率
     */
    private BigDecimal incomeRate;

    /**
     * 万元收益
     */
    private BigDecimal percentIncome;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
     * 获取金飞镖业务流水号
     *
     * @return serial_number - 金飞镖业务流水号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置金飞镖业务流水号
     *
     * @param serialNumber 金飞镖业务流水号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    /**
     * 获取收益计提日期
     *
     * @return apply_date - 收益计提日期
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * 设置收益计提日期
     *
     * @param applyDate 收益计提日期
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 获取收益确认日期
     *
     * @return confirm_date - 收益确认日期
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置收益确认日期
     *
     * @param confirmDate 收益确认日期
     */
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * 获取收益处理状态：0000-成功，0010-失败
     *
     * @return income_state - 收益处理状态：0000-成功，0010-失败
     */
    public String getIncomeState() {
        return incomeState;
    }

    /**
     * 设置收益处理状态：0000-成功，0010-失败
     *
     * @param incomeState 收益处理状态：0000-成功，0010-失败
     */
    public void setIncomeState(String incomeState) {
        this.incomeState = incomeState == null ? null : incomeState.trim();
    }

    /**
     * 获取汇总日期
     *
     * @return gather_date - 汇总日期
     */
    public Date getGatherDate() {
        return gatherDate;
    }

    /**
     * 设置汇总日期
     *
     * @param gatherDate 汇总日期
     */
    public void setGatherDate(Date gatherDate) {
        this.gatherDate = gatherDate;
    }

    /**
     * 获取批次号
     *
     * @return batch_code - 批次号
     */
    public Integer getBatchCode() {
        return batchCode;
    }

    /**
     * 设置批次号
     *
     * @param batchCode 批次号
     */
    public void setBatchCode(Integer batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * 获取投资人Id,导入渠道数据后由平台生成
     *
     * @return investor_id - 投资人Id,导入渠道数据后由平台生成
     */
    public String getInvestorId() {
        return investorId;
    }

    /**
     * 设置投资人Id,导入渠道数据后由平台生成
     *
     * @param investorId 投资人Id,导入渠道数据后由平台生成
     */
    public void setInvestorId(String investorId) {
        this.investorId = investorId == null ? null : investorId.trim();
    }

    /**
     * 获取渠道用户id
     *
     * @return chn_id - 渠道用户id
     */
    public String getChnId() {
        return chnId;
    }

    /**
     * 设置渠道用户id
     *
     * @param chnId 渠道用户id
     */
    public void setChnId(String chnId) {
        this.chnId = chnId == null ? null : chnId.trim();
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

    /**
     * 获取投资人姓名
     *
     * @return name - 投资人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置投资人姓名
     *
     * @param name 投资人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 获取子产品代码
     *
     * @return child_product_no - 子产品代码
     */
    public String getChildProductNo() {
        return childProductNo;
    }

    /**
     * 设置子产品代码
     *
     * @param childProductNo 子产品代码
     */
    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo == null ? null : childProductNo.trim();
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
     * 获取持有金额
     *
     * @return total_postion_amount - 持有金额
     */
    public BigDecimal getTotalPostionAmount() {
        return totalPostionAmount;
    }

    /**
     * 设置持有金额
     *
     * @param totalPostionAmount 持有金额
     */
    public void setTotalPostionAmount(BigDecimal totalPostionAmount) {
        this.totalPostionAmount = totalPostionAmount;
    }

    /**
     * 获取收益金额：定期产品，为固定期限后的收益；活期产品为每日收益
     *
     * @return income_amount - 收益金额：定期产品，为固定期限后的收益；活期产品为每日收益
     */
    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    /**
     * 设置收益金额：定期产品，为固定期限后的收益；活期产品为每日收益
     *
     * @param incomeAmount 收益金额：定期产品，为固定期限后的收益；活期产品为每日收益
     */
    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
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
     * @return percent_income - 万元收益
     */
    public BigDecimal getPercentIncome() {
        return percentIncome;
    }

    /**
     * 设置万元收益
     *
     * @param percentIncome 万元收益
     */
    public void setPercentIncome(BigDecimal percentIncome) {
        this.percentIncome = percentIncome;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}