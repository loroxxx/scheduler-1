package com.jinhui.scheduler.domain.zlrt;

public class BatchState {
    private Integer batchCode;

    private String date;

    private String createTime;

    private String clearTask;

    public Integer getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(Integer batchCode) {
        this.batchCode = batchCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClearTask() {
        return clearTask;
    }

    public void setClearTask(String clearTask) {
        this.clearTask = clearTask;
    }

    @Override
    public String toString() {
        return "BatchState{" +
                "batchCode=" + batchCode +
                ", date='" + date + '\'' +
                ", createTime='" + createTime + '\'' +
                ", clearTask='" + clearTask + '\'' +
                '}';
    }
}