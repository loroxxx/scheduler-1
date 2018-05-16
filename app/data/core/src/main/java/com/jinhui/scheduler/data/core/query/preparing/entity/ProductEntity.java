package com.jinhui.scheduler.data.core.query.preparing.entity;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ProductEntity {

    private String productNo;
    private String productName;
    //认购金额金额
    private BigDecimal subscribeAmount;
    //赎回金额金额
    private BigDecimal redeemAmount;
    //当日申购限额
    private BigDecimal subscribeLimit;
    //当日赎回限额
    private BigDecimal redeemLimit;
    //产品规模
    private BigDecimal scale;
    //产品限额
    private BigDecimal scaleLimit;
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

    public BigDecimal getSubscribeAmount() {
        return subscribeAmount;
    }

    public void setSubscribeAmount(BigDecimal subscribeAmount) {
        this.subscribeAmount = subscribeAmount;
    }

    public BigDecimal getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(BigDecimal redeemAmount) {
        this.redeemAmount = redeemAmount;
    }

    public BigDecimal getSubscribeLimit() {
        return subscribeLimit;
    }

    public void setSubscribeLimit(BigDecimal subscribeLimit) {
        this.subscribeLimit = subscribeLimit;
    }

    public BigDecimal getRedeemLimit() {
        return redeemLimit;
    }

    public void setRedeemLimit(BigDecimal redeemLimit) {
        this.redeemLimit = redeemLimit;
    }

    public BigDecimal getScale() {
        return scale;
    }

    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    public void applyHistScale(BigDecimal histScale) {
        if(histScale == null)
            histScale = BigDecimal.ZERO;
        this.scale = histScale.add(subscribeAmount==null?BigDecimal.ZERO:subscribeAmount).
                subtract(redeemAmount==null?BigDecimal.ZERO:redeemAmount);
    }

    public BigDecimal getScaleLimit() {
        return scaleLimit;
    }

    public void setScaleLimit(BigDecimal scaleLimit) {
        this.scaleLimit = scaleLimit;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", subscribeAmount=" + subscribeAmount +
                ", redeemAmount=" + redeemAmount +
                ", subscribeLimit=" + subscribeLimit +
                ", redeemLimit=" + redeemLimit +
                ", scale=" + scale +
                ", scaleLimit=" + scaleLimit +
                ", batchDate='" + batchDate + '\'' +
                '}';
    }
}
