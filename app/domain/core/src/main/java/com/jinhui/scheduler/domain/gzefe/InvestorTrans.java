package com.jinhui.scheduler.domain.gzefe;

import java.math.BigDecimal;
import java.util.Date;

public class InvestorTrans {
    /**
     * 表主键
     */
    private Integer id;

    /**
     * 金飞镖业务流水号
     */
    private String serialNumber;

    /**
     * 商户系统流水号
     */
    private String zlFundSeqid;

    /**
     * 证联交易日期
     */
    private String zlPnrDate;

    /**
     * 证联交易时间戳
     */
    private String zlPnrTime;

    /**
     * 证联支付流水号
     */
    private String zlPnrSeqid;

    /**
     * 批次号
     */
    private Integer batchCode;

    /**
     * 投资人id,导入渠道数据后由平台生成
     */
    private String investorId;

    /**
     * 渠道用户id
     */
    private String chnId;

    /**
     * 渠道代码
     */
    private String chnCode;

    /**
     * 投资人姓名
     */
    private String name;

    /**
     * 交易时间
     */
    private Date transTime;

    /**
     * 确认日期
     */
    private Date confirmDate;

    /**
     * 交易处理状态：0000-成功，0010-失败
     */
    private String transState;

    /**
     * 是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
     */
    private String isExcess;

    /**
     * 申请单编号
     */
    private String appSheetSerialNo;

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
     * 交易类型，022-申购，024-赎回 099-定期产品全额赎回(清算时由系统生成)
     */
    private String transType;

    /**
     * 交易份额
     */
    private BigDecimal transVol;

    /**
     * 交易金额，用于平台内部统计使用
     */
    private BigDecimal transAmount;

    /**
     * 产品每份交易金额
     */
    private BigDecimal productVol;

    /**
     * 手续费
     */
    private BigDecimal transFix;

    /**
     * 带走收益标志：0-不带走,1-带走
     */
    private String takeIncomeFlag;

    /**
     * 巨额购买处理标志:0-取消，1-顺延
     */
    private String hugeSubsFlag;

    /**
     * 巨额赎回处理标志:0-取消，1-顺延
     */
    private String hugeRedemFlag;

    /**
     * 用户是否签署风险揭示书：0-已签署，1-未签署
     */
    private String riskDisclosure;

    /**
     * 交易银行名称
     */
    private String transBankName;

    /**
     * 交易银行账号
     */
    private String transBankAct;

    /**
     * 收费方式：0-前端收费，1-后端收费，2-混合收费
     */
    private String chargeType;

    /**
     * 收费类型：1-指定费率，2-指定费用
     */
    private String chargeWay;

    /**
     * 指定费率
     */
    private BigDecimal chargeRate;

    /**
     * 指定费用
     */
    private BigDecimal specifyFee;

    /**
     * 滚存标志：0-关闭，1-开启
     */
    private String rollingFlag;

    /**
     * 强行赎回原因：0-小于最低持有数，1-司法执行，2-政策原因
     */
    private String forceRedemReason;

    /**
     * 强制赎回类型：0-强制赎回，1-违约赎回，2-到期
     */
    private String forceRedemType;

    /**
     * 赎回方式 0 赎回到用户银行卡  1 赎回到用户虚户
     */
    private String redeemFlag;

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
     * 获取商户系统流水号
     *
     * @return zl_fund_seqId - 商户系统流水号
     */
    public String getZlFundSeqid() {
        return zlFundSeqid;
    }

    /**
     * 设置商户系统流水号
     *
     * @param zlFundSeqid 商户系统流水号
     */
    public void setZlFundSeqid(String zlFundSeqid) {
        this.zlFundSeqid = zlFundSeqid == null ? null : zlFundSeqid.trim();
    }

    /**
     * 获取证联交易日期
     *
     * @return zl_pnr_date - 证联交易日期
     */
    public String getZlPnrDate() {
        return zlPnrDate;
    }

    /**
     * 设置证联交易日期
     *
     * @param zlPnrDate 证联交易日期
     */
    public void setZlPnrDate(String zlPnrDate) {
        this.zlPnrDate = zlPnrDate == null ? null : zlPnrDate.trim();
    }

    /**
     * 获取证联交易时间戳
     *
     * @return zl_pnr_time - 证联交易时间戳
     */
    public String getZlPnrTime() {
        return zlPnrTime;
    }

    /**
     * 设置证联交易时间戳
     *
     * @param zlPnrTime 证联交易时间戳
     */
    public void setZlPnrTime(String zlPnrTime) {
        this.zlPnrTime = zlPnrTime == null ? null : zlPnrTime.trim();
    }

    /**
     * 获取证联支付流水号
     *
     * @return zl_pnr_seqid - 证联支付流水号
     */
    public String getZlPnrSeqid() {
        return zlPnrSeqid;
    }

    /**
     * 设置证联支付流水号
     *
     * @param zlPnrSeqid 证联支付流水号
     */
    public void setZlPnrSeqid(String zlPnrSeqid) {
        this.zlPnrSeqid = zlPnrSeqid == null ? null : zlPnrSeqid.trim();
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
     * 获取投资人id,导入渠道数据后由平台生成
     *
     * @return investor_id - 投资人id,导入渠道数据后由平台生成
     */
    public String getInvestorId() {
        return investorId;
    }

    /**
     * 设置投资人id,导入渠道数据后由平台生成
     *
     * @param investorId 投资人id,导入渠道数据后由平台生成
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
     * 获取渠道代码
     *
     * @return chn_code - 渠道代码
     */
    public String getChnCode() {
        return chnCode;
    }

    /**
     * 设置渠道代码
     *
     * @param chnCode 渠道代码
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
     * 获取交易时间
     *
     * @return trans_time - 交易时间
     */
    public Date getTransTime() {
        return transTime;
    }

    /**
     * 设置交易时间
     *
     * @param transTime 交易时间
     */
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    /**
     * 获取确认日期
     *
     * @return confirm_date - 确认日期
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置确认日期
     *
     * @param confirmDate 确认日期
     */
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * 获取交易处理状态：0000-成功，0010-失败
     *
     * @return trans_state - 交易处理状态：0000-成功，0010-失败
     */
    public String getTransState() {
        return transState;
    }

    /**
     * 设置交易处理状态：0000-成功，0010-失败
     *
     * @param transState 交易处理状态：0000-成功，0010-失败
     */
    public void setTransState(String transState) {
        this.transState = transState == null ? null : transState.trim();
    }

    /**
     * 获取是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
     *
     * @return is_excess - 是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
     */
    public String getIsExcess() {
        return isExcess;
    }

    /**
     * 设置是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
     *
     * @param isExcess 是否超额 0否  1客户当日申购金额超限 2客户当日赎回金额超限 3客户当日赎回金额超过持仓金额
     */
    public void setIsExcess(String isExcess) {
        this.isExcess = isExcess == null ? null : isExcess.trim();
    }

    /**
     * 获取申请单编号
     *
     * @return app_sheet_serial_no - 申请单编号
     */
    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }

    /**
     * 设置申请单编号
     *
     * @param appSheetSerialNo 申请单编号
     */
    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo == null ? null : appSheetSerialNo.trim();
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
     * 获取交易类型，022-申购，024-赎回 099-定期产品全额赎回(清算时由系统生成)
     *
     * @return trans_type - 交易类型，022-申购，024-赎回 099-定期产品全额赎回(清算时由系统生成)
     */
    public String getTransType() {
        return transType;
    }

    /**
     * 设置交易类型，022-申购，024-赎回 099-定期产品全额赎回(清算时由系统生成)
     *
     * @param transType 交易类型，022-申购，024-赎回 099-定期产品全额赎回(清算时由系统生成)
     */
    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    /**
     * 获取交易份额
     *
     * @return trans_vol - 交易份额
     */
    public BigDecimal getTransVol() {
        return transVol;
    }

    /**
     * 设置交易份额
     *
     * @param transVol 交易份额
     */
    public void setTransVol(BigDecimal transVol) {
        this.transVol = transVol;
    }

    /**
     * 获取交易金额，用于平台内部统计使用
     *
     * @return trans_amount - 交易金额，用于平台内部统计使用
     */
    public BigDecimal getTransAmount() {
        return transAmount;
    }

    /**
     * 设置交易金额，用于平台内部统计使用
     *
     * @param transAmount 交易金额，用于平台内部统计使用
     */
    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    /**
     * 获取产品每份交易金额
     *
     * @return product_vol - 产品每份交易金额
     */
    public BigDecimal getProductVol() {
        return productVol;
    }

    /**
     * 设置产品每份交易金额
     *
     * @param productVol 产品每份交易金额
     */
    public void setProductVol(BigDecimal productVol) {
        this.productVol = productVol;
    }

    /**
     * 获取手续费
     *
     * @return trans_fix - 手续费
     */
    public BigDecimal getTransFix() {
        return transFix;
    }

    /**
     * 设置手续费
     *
     * @param transFix 手续费
     */
    public void setTransFix(BigDecimal transFix) {
        this.transFix = transFix;
    }

    /**
     * 获取带走收益标志：0-不带走,1-带走
     *
     * @return take_income_flag - 带走收益标志：0-不带走,1-带走
     */
    public String getTakeIncomeFlag() {
        return takeIncomeFlag;
    }

    /**
     * 设置带走收益标志：0-不带走,1-带走
     *
     * @param takeIncomeFlag 带走收益标志：0-不带走,1-带走
     */
    public void setTakeIncomeFlag(String takeIncomeFlag) {
        this.takeIncomeFlag = takeIncomeFlag == null ? null : takeIncomeFlag.trim();
    }

    /**
     * 获取巨额购买处理标志:0-取消，1-顺延
     *
     * @return huge_subs_flag - 巨额购买处理标志:0-取消，1-顺延
     */
    public String getHugeSubsFlag() {
        return hugeSubsFlag;
    }

    /**
     * 设置巨额购买处理标志:0-取消，1-顺延
     *
     * @param hugeSubsFlag 巨额购买处理标志:0-取消，1-顺延
     */
    public void setHugeSubsFlag(String hugeSubsFlag) {
        this.hugeSubsFlag = hugeSubsFlag == null ? null : hugeSubsFlag.trim();
    }

    /**
     * 获取巨额赎回处理标志:0-取消，1-顺延
     *
     * @return huge_redem_flag - 巨额赎回处理标志:0-取消，1-顺延
     */
    public String getHugeRedemFlag() {
        return hugeRedemFlag;
    }

    /**
     * 设置巨额赎回处理标志:0-取消，1-顺延
     *
     * @param hugeRedemFlag 巨额赎回处理标志:0-取消，1-顺延
     */
    public void setHugeRedemFlag(String hugeRedemFlag) {
        this.hugeRedemFlag = hugeRedemFlag == null ? null : hugeRedemFlag.trim();
    }

    /**
     * 获取用户是否签署风险揭示书：0-已签署，1-未签署
     *
     * @return risk_disclosure - 用户是否签署风险揭示书：0-已签署，1-未签署
     */
    public String getRiskDisclosure() {
        return riskDisclosure;
    }

    /**
     * 设置用户是否签署风险揭示书：0-已签署，1-未签署
     *
     * @param riskDisclosure 用户是否签署风险揭示书：0-已签署，1-未签署
     */
    public void setRiskDisclosure(String riskDisclosure) {
        this.riskDisclosure = riskDisclosure == null ? null : riskDisclosure.trim();
    }

    /**
     * 获取交易银行名称
     *
     * @return trans_bank_name - 交易银行名称
     */
    public String getTransBankName() {
        return transBankName;
    }

    /**
     * 设置交易银行名称
     *
     * @param transBankName 交易银行名称
     */
    public void setTransBankName(String transBankName) {
        this.transBankName = transBankName == null ? null : transBankName.trim();
    }

    /**
     * 获取交易银行账号
     *
     * @return trans_bank_act - 交易银行账号
     */
    public String getTransBankAct() {
        return transBankAct;
    }

    /**
     * 设置交易银行账号
     *
     * @param transBankAct 交易银行账号
     */
    public void setTransBankAct(String transBankAct) {
        this.transBankAct = transBankAct == null ? null : transBankAct.trim();
    }

    /**
     * 获取收费方式：0-前端收费，1-后端收费，2-混合收费
     *
     * @return charge_type - 收费方式：0-前端收费，1-后端收费，2-混合收费
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * 设置收费方式：0-前端收费，1-后端收费，2-混合收费
     *
     * @param chargeType 收费方式：0-前端收费，1-后端收费，2-混合收费
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    /**
     * 获取收费类型：1-指定费率，2-指定费用
     *
     * @return charge_way - 收费类型：1-指定费率，2-指定费用
     */
    public String getChargeWay() {
        return chargeWay;
    }

    /**
     * 设置收费类型：1-指定费率，2-指定费用
     *
     * @param chargeWay 收费类型：1-指定费率，2-指定费用
     */
    public void setChargeWay(String chargeWay) {
        this.chargeWay = chargeWay == null ? null : chargeWay.trim();
    }

    /**
     * 获取指定费率
     *
     * @return charge_rate - 指定费率
     */
    public BigDecimal getChargeRate() {
        return chargeRate;
    }

    /**
     * 设置指定费率
     *
     * @param chargeRate 指定费率
     */
    public void setChargeRate(BigDecimal chargeRate) {
        this.chargeRate = chargeRate;
    }

    /**
     * 获取指定费用
     *
     * @return specify_fee - 指定费用
     */
    public BigDecimal getSpecifyFee() {
        return specifyFee;
    }

    /**
     * 设置指定费用
     *
     * @param specifyFee 指定费用
     */
    public void setSpecifyFee(BigDecimal specifyFee) {
        this.specifyFee = specifyFee;
    }

    /**
     * 获取滚存标志：0-关闭，1-开启
     *
     * @return rolling_flag - 滚存标志：0-关闭，1-开启
     */
    public String getRollingFlag() {
        return rollingFlag;
    }

    /**
     * 设置滚存标志：0-关闭，1-开启
     *
     * @param rollingFlag 滚存标志：0-关闭，1-开启
     */
    public void setRollingFlag(String rollingFlag) {
        this.rollingFlag = rollingFlag == null ? null : rollingFlag.trim();
    }

    /**
     * 获取强行赎回原因：0-小于最低持有数，1-司法执行，2-政策原因
     *
     * @return force_redem_reason - 强行赎回原因：0-小于最低持有数，1-司法执行，2-政策原因
     */
    public String getForceRedemReason() {
        return forceRedemReason;
    }

    /**
     * 设置强行赎回原因：0-小于最低持有数，1-司法执行，2-政策原因
     *
     * @param forceRedemReason 强行赎回原因：0-小于最低持有数，1-司法执行，2-政策原因
     */
    public void setForceRedemReason(String forceRedemReason) {
        this.forceRedemReason = forceRedemReason == null ? null : forceRedemReason.trim();
    }

    /**
     * 获取强制赎回类型：0-强制赎回，1-违约赎回，2-到期
     *
     * @return force_redem_type - 强制赎回类型：0-强制赎回，1-违约赎回，2-到期
     */
    public String getForceRedemType() {
        return forceRedemType;
    }

    /**
     * 设置强制赎回类型：0-强制赎回，1-违约赎回，2-到期
     *
     * @param forceRedemType 强制赎回类型：0-强制赎回，1-违约赎回，2-到期
     */
    public void setForceRedemType(String forceRedemType) {
        this.forceRedemType = forceRedemType == null ? null : forceRedemType.trim();
    }

    /**
     * 获取赎回方式 0 赎回到用户银行卡  1 赎回到用户虚户
     *
     * @return redeem_flag - 赎回方式 0 赎回到用户银行卡  1 赎回到用户虚户
     */
    public String getRedeemFlag() {
        return redeemFlag;
    }

    /**
     * 设置赎回方式 0 赎回到用户银行卡  1 赎回到用户虚户
     *
     * @param redeemFlag 赎回方式 0 赎回到用户银行卡  1 赎回到用户虚户
     */
    public void setRedeemFlag(String redeemFlag) {
        this.redeemFlag = redeemFlag == null ? null : redeemFlag.trim();
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