package com.jinhui.scheduler.data.core.query.preparing.entity;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TAEntity {

    private String taCode;
    private String taName;
    private BigDecimal issueScale;
    private BigDecimal issueLimit;
    //汇总日期
    private String batchDate;

    public void applyHistIssueScale(BigDecimal histIssueScale){
        if(histIssueScale == null)
            histIssueScale = BigDecimal.ZERO;
        this.issueScale = issueScale.add(histIssueScale);
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode;
    }

    public String getTaName() {
        return taName;
    }

    public void setTaName(String taName) {
        this.taName = taName;
    }

    public BigDecimal getIssueScale() {
        return issueScale;
    }

    public void setIssueScale(BigDecimal issueScale) {
        this.issueScale = issueScale;
    }

    public BigDecimal getIssueLimit() {
        return issueLimit;
    }

    public void setIssueLimit(BigDecimal issueLimit) {
        this.issueLimit = issueLimit;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }
}
