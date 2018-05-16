package com.jinhui.scheduler.domain.core;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public class ProductQueryParam {

    private String productType;
    private String productNo;
    private String exchangeNo;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo;
    }

    @Override
    public String toString() {
        return "ProductParam{" +
                "productType='" + productType + '\'' +
                ", productNo='" + productNo + '\'' +
                ", exchangeNo='" + exchangeNo + '\'' +
                '}';
    }
}
