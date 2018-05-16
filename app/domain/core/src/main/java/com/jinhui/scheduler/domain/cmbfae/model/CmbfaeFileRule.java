package com.jinhui.scheduler.domain.cmbfae.model;

import java.util.Date;


/**
 * 控制每天的文件生成标识
 *
 * @author luoyuanq
 * @date 2017-06-15 17:57:39
 */
public class CmbfaeFileRule {


    //批次时间
    private Date batchDate;


    //Y表示可以生成文件，N表示当天不能再生成文件
    private String flag;

    //success表示生成成功，fail表示生产失败
    private String result;


    public CmbfaeFileRule() {
        super();
    }

    public CmbfaeFileRule(Date batchDate, String flag) {
        this.batchDate = batchDate;
        this.flag = flag;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
