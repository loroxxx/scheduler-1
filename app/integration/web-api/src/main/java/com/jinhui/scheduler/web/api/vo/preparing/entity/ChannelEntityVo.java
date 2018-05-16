package com.jinhui.scheduler.web.api.vo.preparing.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ChannelEntityVo {

    @ApiModelProperty(value="渠道代码")
    private String channelCode;
    @ApiModelProperty(value="渠道名")
    private String channelName;
    @ApiModelProperty(value="渠道销售规模")
    private BigDecimal sellScale;
    @ApiModelProperty(value="渠道销售限额")
    private BigDecimal sellLimit;
    //汇总日期
    @ApiModelProperty(value="批次日期")
    private String batchDate;

    public void applyHistIssueScale(BigDecimal histIssueScale){
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
