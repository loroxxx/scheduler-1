package com.jinhui.scheduler.domain.core;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
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
     * 产品发行方
     */
    private String productIssuer;

    /**
     * 交易所代码
     */
    private String exchangeNo;

    /**
     * 产品子类
     */
    private String subClass;

    /**
     * 产品类型:0-活期,1-定期,2-其他
     */
    private String productType;

    /**
     * 产品期限
     */
    private Integer productExpdate;

    /**
     * 期限单位：0-日，1-月，2-年
     */
    private Integer expdateUnit;

    /**
     * 收益计算类型:0-浮动收益,1-固定收益，2-固定+浮动，3-额度浮动，4-净值浮动，5-期限浮动，6-分配收益，7-约定收益 
     */
    private String calIncomeWay;

    /**
     * 产品计息方式:0-ACT/360(1年期以内的产品，年度天数基数360天),1-ACT/365(1年期及以上的产品，年度天数基数365天),2-月
     */
    private String calRateWay;

    /**
     * 产品成立日(活期产品)
     */
    private Date setupDate;

    /**
     * 产品起息日(定期产品)
     */
    private Date rateDate;

    /**
     * 产品到期日(定期产品)
     */
    private Date termDate;

    /**
     * 产品兑付日
     */
    private Date casheDate;

    /**
     * 产品收益率
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
     * 募集起始时间
     */
    private Date ipoStartDate;

    /**
     * 募集结束时间
     */
    private Date ipoEndDate;

    /**
     * 产品总期数(定期产品)
     */
    private Integer productTotalPeriods;

    /**
     * 起始期数(定期产品)
     */
    private Integer startPeriods;

    /**
     * 结束期数(定期产品)
     */
    private Integer endPeriods;

    /**
     * 产品总额度
     */
    private BigDecimal totalLimit;

    /**
     * 产品总份额
     */
    private BigDecimal totalVol;

    /**
     * 产品每份金额
     */
    private BigDecimal productVol;

    /**
     * 购买起始金额
     */
    private BigDecimal subsStartAmount;

    /**
     * 增减幅度
     */
    private BigDecimal subsRange;

    /**
     * 是否需要风险测评：1-是，0-否
     */
    private String riskAssess;

    /**
     * 风险级别：1-低，2-较低，3-中，4-较高，5-高
     */
    private String riskLevel;

    /**
     * 定期计算方式:收益分配方式：1-按季付息，到期一次还本;2-按年付息,到期一次还本;3-到期一次性还本付息;4-按半年付息,到期一次性还本;(一年期或以上按季的天数间隔：91、91、91、92;六个月按季的天数间隔：90、90)
     */
    private String interestType;

    /**
     * 01-推荐期(导入产品要素后，在产品成立日清算前);02-存续期(产品成立日后，产品到期日清算前);03-产品终止
     */
    private String productState;

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
     * 获取产品发行方
     *
     * @return product_issuer - 产品发行方
     */
    public String getProductIssuer() {
        return productIssuer;
    }

    /**
     * 设置产品发行方
     *
     * @param productIssuer 产品发行方
     */
    public void setProductIssuer(String productIssuer) {
        this.productIssuer = productIssuer == null ? null : productIssuer.trim();
    }

    /**
     * 获取交易所代码
     *
     * @return exchange_no - 交易所代码
     */
    public String getExchangeNo() {
        return exchangeNo;
    }

    /**
     * 设置交易所代码
     *
     * @param exchangeNo 交易所代码
     */
    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo == null ? null : exchangeNo.trim();
    }

    /**
     * 获取产品子类
     *
     * @return sub_class - 产品子类
     */
    public String getSubClass() {
        return subClass;
    }

    /**
     * 设置产品子类
     *
     * @param subClass 产品子类
     */
    public void setSubClass(String subClass) {
        this.subClass = subClass == null ? null : subClass.trim();
    }

    /**
     * 获取产品类型:0-活期,1-定期,2-其他
     *
     * @return product_type - 产品类型:0-活期,1-定期,2-其他
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置产品类型:0-活期,1-定期,2-其他
     *
     * @param productType 产品类型:0-活期,1-定期,2-其他
     */
    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    /**
     * 获取产品期限
     *
     * @return product_expdate - 产品期限
     */
    public Integer getProductExpdate() {
        return productExpdate;
    }

    /**
     * 设置产品期限
     *
     * @param productExpdate 产品期限
     */
    public void setProductExpdate(Integer productExpdate) {
        this.productExpdate = productExpdate;
    }

    /**
     * 获取期限单位：0-日，1-月，2-年
     *
     * @return expdate_unit - 期限单位：0-日，1-月，2-年
     */
    public Integer getExpdateUnit() {
        return expdateUnit;
    }

    /**
     * 设置期限单位：0-日，1-月，2-年
     *
     * @param expdateUnit 期限单位：0-日，1-月，2-年
     */
    public void setExpdateUnit(Integer expdateUnit) {
        this.expdateUnit = expdateUnit;
    }

    /**
     * 获取收益计算类型:0-浮动收益,1-固定收益，2-固定+浮动，3-额度浮动，4-净值浮动，5-期限浮动，6-分配收益，7-约定收益 
     *
     * @return cal_income_way - 收益计算类型:0-浮动收益,1-固定收益，2-固定+浮动，3-额度浮动，4-净值浮动，5-期限浮动，6-分配收益，7-约定收益 
     */
    public String getCalIncomeWay() {
        return calIncomeWay;
    }

    /**
     * 设置收益计算类型:0-浮动收益,1-固定收益，2-固定+浮动，3-额度浮动，4-净值浮动，5-期限浮动，6-分配收益，7-约定收益 
     *
     * @param calIncomeWay 收益计算类型:0-浮动收益,1-固定收益，2-固定+浮动，3-额度浮动，4-净值浮动，5-期限浮动，6-分配收益，7-约定收益 
     */
    public void setCalIncomeWay(String calIncomeWay) {
        this.calIncomeWay = calIncomeWay == null ? null : calIncomeWay.trim();
    }

    /**
     * 获取产品计息方式:0-ACT/360(1年期以内的产品，年度天数基数360天),1-ACT/365(1年期及以上的产品，年度天数基数365天),2-月
     *
     * @return cal_rate_way - 产品计息方式:0-ACT/360(1年期以内的产品，年度天数基数360天),1-ACT/365(1年期及以上的产品，年度天数基数365天),2-月
     */
    public String getCalRateWay() {
        return calRateWay;
    }

    /**
     * 设置产品计息方式:0-ACT/360(1年期以内的产品，年度天数基数360天),1-ACT/365(1年期及以上的产品，年度天数基数365天),2-月
     *
     * @param calRateWay 产品计息方式:0-ACT/360(1年期以内的产品，年度天数基数360天),1-ACT/365(1年期及以上的产品，年度天数基数365天),2-月
     */
    public void setCalRateWay(String calRateWay) {
        this.calRateWay = calRateWay == null ? null : calRateWay.trim();
    }

    /**
     * 获取产品成立日(活期产品)
     *
     * @return setup_date - 产品成立日(活期产品)
     */
    public Date getSetupDate() {
        return setupDate;
    }

    /**
     * 设置产品成立日(活期产品)
     *
     * @param setupDate 产品成立日(活期产品)
     */
    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    /**
     * 获取产品起息日(定期产品)
     *
     * @return rate_date - 产品起息日(定期产品)
     */
    public Date getRateDate() {
        return rateDate;
    }

    /**
     * 设置产品起息日(定期产品)
     *
     * @param rateDate 产品起息日(定期产品)
     */
    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    /**
     * 获取产品到期日(定期产品)
     *
     * @return term_date - 产品到期日(定期产品)
     */
    public Date getTermDate() {
        return termDate;
    }

    /**
     * 设置产品到期日(定期产品)
     *
     * @param termDate 产品到期日(定期产品)
     */
    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    /**
     * 获取产品兑付日
     *
     * @return cashe_date - 产品兑付日
     */
    public Date getCasheDate() {
        return casheDate;
    }

    /**
     * 设置产品兑付日
     *
     * @param casheDate 产品兑付日
     */
    public void setCasheDate(Date casheDate) {
        this.casheDate = casheDate;
    }

    /**
     * 获取产品收益率
     *
     * @return income_rate - 产品收益率
     */
    public BigDecimal getIncomeRate() {
        return incomeRate;
    }

    /**
     * 设置产品收益率
     *
     * @param incomeRate 产品收益率
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
     * 获取募集起始时间
     *
     * @return ipo_start_date - 募集起始时间
     */
    public Date getIpoStartDate() {
        return ipoStartDate;
    }

    /**
     * 设置募集起始时间
     *
     * @param ipoStartDate 募集起始时间
     */
    public void setIpoStartDate(Date ipoStartDate) {
        this.ipoStartDate = ipoStartDate;
    }

    /**
     * 获取募集结束时间
     *
     * @return ipo_end_date - 募集结束时间
     */
    public Date getIpoEndDate() {
        return ipoEndDate;
    }

    /**
     * 设置募集结束时间
     *
     * @param ipoEndDate 募集结束时间
     */
    public void setIpoEndDate(Date ipoEndDate) {
        this.ipoEndDate = ipoEndDate;
    }

    /**
     * 获取产品总期数(定期产品)
     *
     * @return product_total_periods - 产品总期数(定期产品)
     */
    public Integer getProductTotalPeriods() {
        return productTotalPeriods;
    }

    /**
     * 设置产品总期数(定期产品)
     *
     * @param productTotalPeriods 产品总期数(定期产品)
     */
    public void setProductTotalPeriods(Integer productTotalPeriods) {
        this.productTotalPeriods = productTotalPeriods;
    }

    /**
     * 获取起始期数(定期产品)
     *
     * @return start_periods - 起始期数(定期产品)
     */
    public Integer getStartPeriods() {
        return startPeriods;
    }

    /**
     * 设置起始期数(定期产品)
     *
     * @param startPeriods 起始期数(定期产品)
     */
    public void setStartPeriods(Integer startPeriods) {
        this.startPeriods = startPeriods;
    }

    /**
     * 获取结束期数(定期产品)
     *
     * @return end_periods - 结束期数(定期产品)
     */
    public Integer getEndPeriods() {
        return endPeriods;
    }

    /**
     * 设置结束期数(定期产品)
     *
     * @param endPeriods 结束期数(定期产品)
     */
    public void setEndPeriods(Integer endPeriods) {
        this.endPeriods = endPeriods;
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
     * 获取产品总份额
     *
     * @return total_vol - 产品总份额
     */
    public BigDecimal getTotalVol() {
        return totalVol;
    }

    /**
     * 设置产品总份额
     *
     * @param totalVol 产品总份额
     */
    public void setTotalVol(BigDecimal totalVol) {
        this.totalVol = totalVol;
    }

    /**
     * 获取产品每份金额
     *
     * @return product_vol - 产品每份金额
     */
    public BigDecimal getProductVol() {
        return productVol;
    }

    /**
     * 设置产品每份金额
     *
     * @param productVol 产品每份金额
     */
    public void setProductVol(BigDecimal productVol) {
        this.productVol = productVol;
    }

    /**
     * 获取购买起始金额
     *
     * @return subs_start_amount - 购买起始金额
     */
    public BigDecimal getSubsStartAmount() {
        return subsStartAmount;
    }

    /**
     * 设置购买起始金额
     *
     * @param subsStartAmount 购买起始金额
     */
    public void setSubsStartAmount(BigDecimal subsStartAmount) {
        this.subsStartAmount = subsStartAmount;
    }

    /**
     * 获取增减幅度
     *
     * @return subs_range - 增减幅度
     */
    public BigDecimal getSubsRange() {
        return subsRange;
    }

    /**
     * 设置增减幅度
     *
     * @param subsRange 增减幅度
     */
    public void setSubsRange(BigDecimal subsRange) {
        this.subsRange = subsRange;
    }

    /**
     * 获取是否需要风险测评：1-是，0-否
     *
     * @return risk_assess - 是否需要风险测评：1-是，0-否
     */
    public String getRiskAssess() {
        return riskAssess;
    }

    /**
     * 设置是否需要风险测评：1-是，0-否
     *
     * @param riskAssess 是否需要风险测评：1-是，0-否
     */
    public void setRiskAssess(String riskAssess) {
        this.riskAssess = riskAssess == null ? null : riskAssess.trim();
    }

    /**
     * 获取风险级别：1-低，2-较低，3-中，4-较高，5-高
     *
     * @return risk_level - 风险级别：1-低，2-较低，3-中，4-较高，5-高
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置风险级别：1-低，2-较低，3-中，4-较高，5-高
     *
     * @param riskLevel 风险级别：1-低，2-较低，3-中，4-较高，5-高
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 获取定期计算方式:收益分配方式：1-按季付息，到期一次还本;2-按年付息,到期一次还本;3-到期一次性还本付息;4-按半年付息,到期一次性还本;(一年期或以上按季的天数间隔：91、91、91、92;六个月按季的天数间隔：90、90)
     *
     * @return interest_type - 定期计算方式:收益分配方式：1-按季付息，到期一次还本;2-按年付息,到期一次还本;3-到期一次性还本付息;4-按半年付息,到期一次性还本;(一年期或以上按季的天数间隔：91、91、91、92;六个月按季的天数间隔：90、90)
     */
    public String getInterestType() {
        return interestType;
    }

    /**
     * 设置定期计算方式:收益分配方式：1-按季付息，到期一次还本;2-按年付息,到期一次还本;3-到期一次性还本付息;4-按半年付息,到期一次性还本;(一年期或以上按季的天数间隔：91、91、91、92;六个月按季的天数间隔：90、90)
     *
     * @param interestType 定期计算方式:收益分配方式：1-按季付息，到期一次还本;2-按年付息,到期一次还本;3-到期一次性还本付息;4-按半年付息,到期一次性还本;(一年期或以上按季的天数间隔：91、91、91、92;六个月按季的天数间隔：90、90)
     */
    public void setInterestType(String interestType) {
        this.interestType = interestType == null ? null : interestType.trim();
    }

    /**
     * 获取01-推荐期(导入产品要素后，在产品成立日清算前);02-存续期(产品成立日后，产品到期日清算前);03-产品终止
     *
     * @return product_state - 01-推荐期(导入产品要素后，在产品成立日清算前);02-存续期(产品成立日后，产品到期日清算前);03-产品终止
     */
    public String getProductState() {
        return productState;
    }

    /**
     * 设置01-推荐期(导入产品要素后，在产品成立日清算前);02-存续期(产品成立日后，产品到期日清算前);03-产品终止
     *
     * @param productState 01-推荐期(导入产品要素后，在产品成立日清算前);02-存续期(产品成立日后，产品到期日清算前);03-产品终止
     */
    public void setProductState(String productState) {
        this.productState = productState == null ? null : productState.trim();
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