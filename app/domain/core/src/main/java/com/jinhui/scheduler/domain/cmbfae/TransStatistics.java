package com.jinhui.scheduler.domain.cmbfae;

/**
 * 交易的统计信息
 * <p>
 * Created by Administrator on 2018/1/17 0017.
 */
public class TransStatistics {

    private String childProductNo;

    private String productName;

    private String tranType;

    private String totalAmount;

    private String transCount;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getChildProductNo() {
        return childProductNo;
    }

    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransCount() {
        return transCount;
    }

    public void setTransCount(String transCount) {
        this.transCount = transCount;
    }
}
