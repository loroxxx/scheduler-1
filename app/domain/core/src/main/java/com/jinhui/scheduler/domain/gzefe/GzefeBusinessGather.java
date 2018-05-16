package com.jinhui.scheduler.domain.gzefe;

import java.math.BigDecimal;
import java.util.Date;

public class GzefeBusinessGather {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 批次号
     */
    private Integer batchCode;

    /**
     * 汇总日期
     */
    private Date gatherDate;

    /**
     * 子产品产品代码
     */
    private String childProductNo;

    /**
     * 渠道代码，渠道在平台中的代码标识
     */
    private String chnCode;

    /**
     * 交易类型，022-申购，024-赎回
     */
    private String transType;

    /**
     * 成功份额汇总
     */
    private BigDecimal successVol;

    /**
     * 失败份额汇总
     */
    private BigDecimal failVol;

    /**
     * 成功金额汇总
     */
    private BigDecimal successAmount;

    /**
     * 失败金额汇总
     */
    private BigDecimal failAmount;

    /**
     * 成功笔数汇总
     */
    private Integer successNum;

    /**
     * 失败笔数汇总
     */
    private Integer failNum;

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
     * 获取子产品产品代码
     *
     * @return child_product_no - 子产品产品代码
     */
    public String getChildProductNo() {
        return childProductNo;
    }

    /**
     * 设置子产品产品代码
     *
     * @param childProductNo 子产品产品代码
     */
    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo == null ? null : childProductNo.trim();
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
     * 获取交易类型，022-申购，024-赎回
     *
     * @return trans_type - 交易类型，022-申购，024-赎回
     */
    public String getTransType() {
        return transType;
    }

    /**
     * 设置交易类型，022-申购，024-赎回
     *
     * @param transType 交易类型，022-申购，024-赎回
     */
    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    /**
     * 获取成功份额汇总
     *
     * @return success_vol - 成功份额汇总
     */
    public BigDecimal getSuccessVol() {
        return successVol;
    }

    /**
     * 设置成功份额汇总
     *
     * @param successVol 成功份额汇总
     */
    public void setSuccessVol(BigDecimal successVol) {
        this.successVol = successVol;
    }

    /**
     * 获取失败份额汇总
     *
     * @return fail_vol - 失败份额汇总
     */
    public BigDecimal getFailVol() {
        return failVol;
    }

    /**
     * 设置失败份额汇总
     *
     * @param failVol 失败份额汇总
     */
    public void setFailVol(BigDecimal failVol) {
        this.failVol = failVol;
    }

    /**
     * 获取成功金额汇总
     *
     * @return success_amount - 成功金额汇总
     */
    public BigDecimal getSuccessAmount() {
        return successAmount;
    }

    /**
     * 设置成功金额汇总
     *
     * @param successAmount 成功金额汇总
     */
    public void setSuccessAmount(BigDecimal successAmount) {
        this.successAmount = successAmount;
    }

    /**
     * 获取失败金额汇总
     *
     * @return fail_amount - 失败金额汇总
     */
    public BigDecimal getFailAmount() {
        return failAmount;
    }

    /**
     * 设置失败金额汇总
     *
     * @param failAmount 失败金额汇总
     */
    public void setFailAmount(BigDecimal failAmount) {
        this.failAmount = failAmount;
    }

    /**
     * 获取成功笔数汇总
     *
     * @return success_num - 成功笔数汇总
     */
    public Integer getSuccessNum() {
        return successNum;
    }

    /**
     * 设置成功笔数汇总
     *
     * @param successNum 成功笔数汇总
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取失败笔数汇总
     *
     * @return fail_num - 失败笔数汇总
     */
    public Integer getFailNum() {
        return failNum;
    }

    /**
     * 设置失败笔数汇总
     *
     * @param failNum 失败笔数汇总
     */
    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }


    @Override
    public String toString() {
        return "GzefeBusinessGather{" +
                "id=" + id +
                ", batchCode=" + batchCode +
                ", gatherDate=" + gatherDate +
                ", childProductNo='" + childProductNo + '\'' +
                ", chnCode='" + chnCode + '\'' +
                ", transType='" + transType + '\'' +
                ", successVol=" + successVol +
                ", failVol=" + failVol +
                ", successAmount=" + successAmount +
                ", failAmount=" + failAmount +
                ", successNum=" + successNum +
                ", failNum=" + failNum +
                '}';
    }
}