package com.jinhui.scheduler.web.api.vo.preparing.criteria;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TACriteriaVo {

    @ApiModelProperty(value="交易所代码")
    private String taCode;
    @ApiModelProperty(value="交易所名")
    private String taName;
    @ApiModelProperty(value="清算日期[格式: yyyy-MM-dd],若为空则默认取当天")
    private String batchDate;

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

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public String toString() {
        return "TACriteriaVo{" +
                "taCode='" + taCode + '\'' +
                ", taName='" + taName + '\'' +
                ", batchDate='" + batchDate + '\'' +
                '}';
    }
}
