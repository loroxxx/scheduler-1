package com.jinhui.scheduler.domain.imiqian.pojo;

/**
 * create wsc  2017-05-26 17:13
 **/
public class BusinessGatherInfo {

    //基金代码
    private String FundCode;
    //销售人代码
    private String DistributorCode;
    //业务代码
    private String BusinessCode;
    //每种业务笔数汇总
    private String AggregationOfTransactionByBusinessType;
    //汇总日期
    private String AggregationDate;
    //失败份数汇总
    private String TotalFailingVol;
    //成功份数汇总
    private String TotalSuccessfulVol;
    //失败金额汇总
    private String TotalFailingAmount;
    //成功金额汇总
    private String TotalSuccessfulAmount;
    //失败交易笔数
    private String TotalFailingDealingNum;
    //成功交易笔数
    private String TotalSuccessfulDealingNum;

    public String getFundCode() {
        return FundCode;
    }

    public void setFundCode(String fundCode) {
        FundCode = fundCode;
    }

    public String getDistributorCode() {
        return DistributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        DistributorCode = distributorCode;
    }

    public String getBusinessCode() {
        return BusinessCode;
    }

    public void setBusinessCode(String businessCode) {
        BusinessCode = businessCode;
    }

    public String getAggregationOfTransactionByBusinessType() {
        return AggregationOfTransactionByBusinessType;
    }

    public void setAggregationOfTransactionByBusinessType(String aggregationOfTransactionByBusinessType) {
        AggregationOfTransactionByBusinessType = aggregationOfTransactionByBusinessType;
    }

    public String getAggregationDate() {
        return AggregationDate;
    }

    public void setAggregationDate(String aggregationDate) {
        AggregationDate = aggregationDate;
    }

    public String getTotalFailingVol() {
        return TotalFailingVol;
    }

    public void setTotalFailingVol(String totalFailingVol) {
        TotalFailingVol = totalFailingVol;
    }

    public String getTotalSuccessfulVol() {
        return TotalSuccessfulVol;
    }

    public void setTotalSuccessfulVol(String totalSuccessfulVol) {
        TotalSuccessfulVol = totalSuccessfulVol;
    }

    public String getTotalFailingAmount() {
        return TotalFailingAmount;
    }

    public void setTotalFailingAmount(String totalFailingAmount) {
        TotalFailingAmount = totalFailingAmount;
    }

    public String getTotalSuccessfulAmount() {
        return TotalSuccessfulAmount;
    }

    public void setTotalSuccessfulAmount(String totalSuccessfulAmount) {
        TotalSuccessfulAmount = totalSuccessfulAmount;
    }

    public String getTotalFailingDealingNum() {
        return TotalFailingDealingNum;
    }

    public void setTotalFailingDealingNum(String totalFailingDealingNum) {
        TotalFailingDealingNum = totalFailingDealingNum;
    }

    public String getTotalSuccessfulDealingNum() {
        return TotalSuccessfulDealingNum;
    }

    public void setTotalSuccessfulDealingNum(String totalSuccessfulDealingNum) {
        TotalSuccessfulDealingNum = totalSuccessfulDealingNum;
    }
}
