package com.jinhui.scheduler.web.api.vo.preparing.criteria;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ChannelCriteriaVo {

    @ApiModelProperty(value="渠道代码")
    private String channelCode;
    @ApiModelProperty(value="渠道名")
    private String channelName;
    //汇总日期
    @ApiModelProperty(value="清算日期[格式: yyyy-MM-dd],若为空则默认取当天")
    private String batchDate;

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

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public String toString() {
        return "ChannelCriteriaVo{" +
                "channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", batchDate='" + batchDate + '\'' +
                '}';
    }
}
