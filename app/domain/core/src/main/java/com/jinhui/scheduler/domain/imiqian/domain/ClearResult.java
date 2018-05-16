package com.jinhui.scheduler.domain.imiqian.domain;

import java.util.Date;

public class ClearResult {
    private Integer id;

    private int batchCode;

    private String batchDate;

    private String chnCode;

    private String stepNo;

    private String stepCode;

    private String stepDesc;

    private int totalCount;

    private int succCount;

    private int failCount;

    private String status;

    private String type;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(int batchCode) {
        this.batchCode = batchCode;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getStepNo() {
        return stepNo;
    }

    public void setStepNo(String stepNo) {
        this.stepNo = stepNo;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSuccCount() {
        return succCount;
    }

    public void setSuccCount(int succCount) {
        this.succCount = succCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ClearResult{" +
                "id=" + id +
                ", batchCode='" + batchCode + '\'' +
                ", batchDate='" + batchDate + '\'' +
                ", chnCode='" + chnCode + '\'' +
                ", stepNo='" + stepNo + '\'' +
                ", stepCode='" + stepCode + '\'' +
                ", stepDesc='" + stepDesc + '\'' +
                ", totalCount=" + totalCount +
                ", succCount=" + succCount +
                ", failCount=" + failCount +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}