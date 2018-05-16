package com.jinhui.scheduler.domain.imiqian.pojo;

public class InvestorPositionInfo {
    //持有人可用基金份数
    private String AvailableVol;
    //基金总份数（含冻结）
    private String TotalVolOfDistributorInTA;
    //TransactionCfmDate
    private String TransactionCfmDate;
    //基金代码
    private String FundCode;
    //投资人基金交易账号
    private String TransactionAccountID;
    //销售人代码
    private String DistributorCode;
    //投资人基金账号
    private String TAAccountID;
    //基金冻结总份数 占位用
    private String TotalFrozenVol;
    //网点号码
    private String BranchCode;
    //明细标志
    private String DetailFlag;
    //占位用
    private String other1;
    //占位用
    private String other2;

    public String getAvailableVol() {
        return AvailableVol;
    }

    public void setAvailableVol(String availableVol) {
        AvailableVol = availableVol;
    }

    public String getTotalVolOfDistributorInTA() {
        return TotalVolOfDistributorInTA;
    }

    public void setTotalVolOfDistributorInTA(String totalVolOfDistributorInTA) {
        TotalVolOfDistributorInTA = totalVolOfDistributorInTA;
    }

    public String getTransactionCfmDate() {
        return TransactionCfmDate;
    }

    public void setTransactionCfmDate(String transactionCfmDate) {
        TransactionCfmDate = transactionCfmDate;
    }

    public String getFundCode() {
        return FundCode;
    }

    public void setFundCode(String fundCode) {
        FundCode = fundCode;
    }

    public String getTransactionAccountID() {
        return TransactionAccountID;
    }

    public void setTransactionAccountID(String transactionAccountID) {
        TransactionAccountID = transactionAccountID;
    }

    public String getDistributorCode() {
        return DistributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        DistributorCode = distributorCode;
    }

    public String getTAAccountID() {
        return TAAccountID;
    }

    public void setTAAccountID(String TAAccountID) {
        this.TAAccountID = TAAccountID;
    }

    public String getTotalFrozenVol() {
        return TotalFrozenVol;
    }

    public void setTotalFrozenVol(String totalFrozenVol) {
        TotalFrozenVol = totalFrozenVol;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getDetailFlag() {
        return DetailFlag;
    }

    public void setDetailFlag(String detailFlag) {
        DetailFlag = detailFlag;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }
}