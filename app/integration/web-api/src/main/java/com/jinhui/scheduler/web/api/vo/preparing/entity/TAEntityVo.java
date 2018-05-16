package com.jinhui.scheduler.web.api.vo.preparing.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TAEntityVo {

    @ApiModelProperty(value="交易所代码")
    private String taCode;
    @ApiModelProperty(value="交易所名")
    private String taName;
    @ApiModelProperty(value="交易所当前发行规模")
    private BigDecimal issueScale;
    @ApiModelProperty(value="交易所发行限额")
    private BigDecimal issueLimit;
    //汇总日期
    @ApiModelProperty(value="批次日期")
    private String batchDate;

    public void applyHistIssueScale(BigDecimal histIssueScale){
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
