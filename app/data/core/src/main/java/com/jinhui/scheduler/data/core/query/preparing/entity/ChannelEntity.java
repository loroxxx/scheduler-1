package com.jinhui.scheduler.data.core.query.preparing.entity;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ChannelEntity {

    private String channelCode;
    private String channelName;
    private BigDecimal sellScale;
    private BigDecimal sellLimit;
    //汇总日期
    private String batchDate;

    public void applyHistIssueScale(BigDecimal histIssueScale){
        if(histIssueScale == null)
            histIssueScale = BigDecimal.ZERO;
        this.sellScale = sellScale.add(histIssueScale);
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public BigDecimal getSellScale() {
        return sellScale;
    }

    public void setSellScale(BigDecimal sellScale) {
        this.sellScale = sellScale;
    }

    public BigDecimal getSellLimit() {
        return sellLimit;
    }

    public void setSellLimit(BigDecimal sellLimit) {
        this.sellLimit = sellLimit;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }
}
