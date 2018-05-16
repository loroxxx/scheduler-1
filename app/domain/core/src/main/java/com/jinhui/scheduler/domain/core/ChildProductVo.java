package com.jinhui.scheduler.domain.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 子产品信息
 * Created by Administrator on 2017/12/25 0025.
 */
public class ChildProductVo {

    private String productNo;//产品代码
    private String productName;//产品名称
    private String childProductNo;//当前子产品使用代码
    private String useQuantity;//子产品使用组数
    private String nonUseQuantity;//剩余子产品组数
    private String platProductNo;//交易所产品代码
    private String useSummary;//使用数量/总数量
    private String addNum;//添加数量
    private String startNo;//子产品起始代码
    private String flow = "1";//是否跟随上一分组,0为跟随，1为不跟随

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;//创建时间

    private String increase;//新增待确认数量

    private boolean isUpload=false;//false表示不需要回传到交易所，true表示生成的子产品需要上传给交易所
    private String exchangeNo;

    public String getUseSummary() {
        return useSummary;
    }

    public void setUseSummary(String useSummary) {
        this.useSummary = useSummary;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
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

    public String getChildProductNo() {
        return childProductNo;
    }

    public void setChildProductNo(String childProductNo) {
        this.childProductNo = childProductNo;
    }

    public String getUseQuantity() {
        return useQuantity;
    }

    public void setUseQuantity(String useQuantity) {
        this.useQuantity = useQuantity;
    }

    public String getNonUseQuantity() {
        return nonUseQuantity;
    }

    public void setNonUseQuantity(String nonUseQuantity) {
        this.nonUseQuantity = nonUseQuantity;
    }

    public String getPlatProductNo() {
        return platProductNo;
    }

    public void setPlatProductNo(String platProductNo) {
        this.platProductNo = platProductNo;
    }

    public String getAddNum() {
        return addNum;
    }

    public void setAddNum(String addNum) {
        this.addNum = addNum;
    }

    public String getStartNo() {
        return startNo;
    }

    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    @Override
    public String toString() {
        return "ChildProductVo{" +
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", childProductNo='" + childProductNo + '\'' +
                ", useQuantity='" + useQuantity + '\'' +
                ", nonUseQuantity='" + nonUseQuantity + '\'' +
                ", platProductNo='" + platProductNo + '\'' +
                ", addNum='" + addNum + '\'' +
                ", startNo='" + startNo + '\'' +
                '}';
    }
}
