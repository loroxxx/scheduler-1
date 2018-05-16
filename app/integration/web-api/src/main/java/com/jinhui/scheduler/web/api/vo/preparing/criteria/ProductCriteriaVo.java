package com.jinhui.scheduler.web.api.vo.preparing.criteria;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ProductCriteriaVo {

   @ApiModelProperty(value="清算日期[格式: yyyy-MM-dd],若为空则默认取当天")
    private String batchDate;
    @ApiModelProperty(value="产品代码")
    private String productNo;
    @ApiModelProperty(value="产品名称")
    private String productName;

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

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

    @Override
    public String toString() {
        return "ProductCriteriaVo{" +
                "batchDate='" + batchDate + '\'' +
                ", productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
