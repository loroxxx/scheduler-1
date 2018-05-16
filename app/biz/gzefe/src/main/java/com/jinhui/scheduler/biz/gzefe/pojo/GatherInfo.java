package com.jinhui.scheduler.biz.gzefe.pojo;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public class GatherInfo {

    private String FundCode;//基金代码
    private String DistributorCode;//销售人代码
    private String BusinessCode;//业务代码
    private String AggregationOfTransactionByBusinessType;//每种业务笔数汇总
    private String AggregationDate;//汇总日期
    private String TotalFailingVol;//失败份数汇总
    private String TotalSuccessfulVol;//成功份数汇总
    private String TotalFailingAmount;//失败金额汇总
    private String TotalSuccessfulAmount;//成功金额汇总
    private String TotalFailingDealingNum;//失败交易笔数
    private String TotalSuccessfulDealingNum;//成功交易笔数


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

    @Override
    public String toString() {
        return "GatherInfo{" +
                "FundCode='" + FundCode + '\'' +
                ", DistributorCode='" + DistributorCode + '\'' +
                ", BusinessCode='" + BusinessCode + '\'' +
                ", AggregationOfTransactionByBusinessType='" + AggregationOfTransactionByBusinessType + '\'' +
                ", AggregationDate='" + AggregationDate + '\'' +
                ", TotalFailingVol='" + TotalFailingVol + '\'' +
                ", TotalSuccessfulVol='" + TotalSuccessfulVol + '\'' +
                ", TotalFailingAmount='" + TotalFailingAmount + '\'' +
                ", TotalSuccessfulAmount='" + TotalSuccessfulAmount + '\'' +
                ", TotalFailingDealingNum='" + TotalFailingDealingNum + '\'' +
                ", TotalSuccessfulDealingNum='" + TotalSuccessfulDealingNum + '\'' +
                '}';
    }
}
