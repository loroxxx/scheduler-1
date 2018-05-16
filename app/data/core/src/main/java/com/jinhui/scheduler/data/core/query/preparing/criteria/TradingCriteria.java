package com.jinhui.scheduler.data.core.query.preparing.criteria;


import com.jinhui.scheduler.data.core.query.base.BasePageCriteria;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TradingCriteria extends BasePageCriteria{
    private String productNo;
    private String productName;
    private String investorId;
    private String investorName;
    //汇总日期
    private String batchDate;

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

}
