package com.jinhui.scheduler.domain.cmbfae;

import java.util.Date;

public class ExchangeFileLog {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 批处理日期
     */
    private Date batchDate;

    /**
     * 批次号
     */
    private Integer batchCode;

    /**
     * 交易所代码
     */
    private String exchangeCode;

    /**
     * 步骤
     */
    private String step;

    /**
     * 步骤代码
     */
    private String stepCode;

    /**
     * 步骤描述
     */
    private String stepDesc;

    /**
     * 数据记录数
     */
    private Integer totalCount;

    /**
     * 状态 0-成功  1-失败
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 文件的生成路径，上传后该路径会失效
     */
    private String filePath;

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
     * 获取批处理日期
     *
     * @return batch_date - 批处理日期
     */
    public Date getBatchDate() {
        return batchDate;
    }

    /**
     * 设置批处理日期
     *
     * @param batchDate 批处理日期
     */
    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
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
     * 获取交易所代码
     *
     * @return exchange_code - 交易所代码
     */
    public String getExchangeCode() {
        return exchangeCode;
    }

    /**
     * 设置交易所代码
     *
     * @param exchangeCode 交易所代码
     */
    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode == null ? null : exchangeCode.trim();
    }

    /**
     * 获取步骤
     *
     * @return step - 步骤
     */
    public String getStep() {
        return step;
    }

    /**
     * 设置步骤
     *
     * @param step 步骤
     */
    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    /**
     * 获取步骤代码
     *
     * @return step_code - 步骤代码
     */
    public String getStepCode() {
        return stepCode;
    }

    /**
     * 设置步骤代码
     *
     * @param stepCode 步骤代码
     */
    public void setStepCode(String stepCode) {
        this.stepCode = stepCode == null ? null : stepCode.trim();
    }

    /**
     * 获取步骤描述
     *
     * @return step_desc - 步骤描述
     */
    public String getStepDesc() {
        return stepDesc;
    }

    /**
     * 设置步骤描述
     *
     * @param stepDesc 步骤描述
     */
    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc == null ? null : stepDesc.trim();
    }

    /**
     * 获取数据记录数
     *
     * @return total_count - 数据记录数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 设置数据记录数
     *
     * @param totalCount 数据记录数
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取状态 0-成功  1-失败
     *
     * @return status - 状态 0-成功  1-失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 0-成功  1-失败
     *
     * @param status 状态 0-成功  1-失败
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}