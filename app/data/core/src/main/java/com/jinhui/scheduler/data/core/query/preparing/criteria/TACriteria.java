package com.jinhui.scheduler.data.core.query.preparing.criteria;


/**
 * Created by jinhui on 2017/6/1.
 */
public class TACriteria {

    private String taCode;
    private String taName;
    //汇总日期
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
}
