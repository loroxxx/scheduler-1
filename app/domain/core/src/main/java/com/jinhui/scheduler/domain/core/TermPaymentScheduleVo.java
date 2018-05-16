package com.jinhui.scheduler.domain.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
public class TermPaymentScheduleVo {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 金飞镖产品代码
     */
    private String productNo;


    /**
     * 单份付息金额(只包含利息)
     */
    private BigDecimal perInterestAmount;


    /**
     * 付息时间
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date interestDate;


    /**
     * 交易所代码
     */
    private String exchangeNo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public BigDecimal getPerInterestAmount() {
        return perInterestAmount;
    }

    public void setPerInterestAmount(BigDecimal perInterestAmount) {
        this.perInterestAmount = perInterestAmount;
    }

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo;
    }

    @Override
    public String toString() {
        return "TermPaymentScheduleVo{" +
                "productName='" + productName + '\'' +
                ", productNo='" + productNo + '\'' +
                ", perInterestAmount=" + perInterestAmount +
                ", interestDate=" + interestDate +
                ", exchangeNo='" + exchangeNo + '\'' +
                '}';
    }
}
