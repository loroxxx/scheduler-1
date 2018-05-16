package com.jinhui.scheduler.data.core.query.preparing.criteria;


/**
 * Created by jinhui on 2017/6/1.
 */
public class ChannelCriteria {

    private String channelCode;
    private String channelName;
    //汇总日期
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
}
