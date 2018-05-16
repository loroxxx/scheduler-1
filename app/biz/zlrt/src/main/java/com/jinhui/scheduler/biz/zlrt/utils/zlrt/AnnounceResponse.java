package com.jinhui.scheduler.biz.zlrt.utils.zlrt;

/**
 * 开户响应参数
 *
 * @autor wsc
 * @create 2018-01-30 9:19
 **/
public class AnnounceResponse {

    //证联支付分配给商户的机构代码
    public String instuId;
    //商户的系统日期(YYYYMMDD)
    public String fundDate;
    //商户的时间戳(HHMMSS)
    public String fundTime;
    //商户系统流水号
    public String fundSeqId;

    //应答码
    public String respCode;
    //业务应答描述
    public String respDesc;

    public String getInstuId() {
        return instuId;
    }

    public void setInstuId(String instuId) {
        this.instuId = instuId;
    }

    public String getFundDate() {
        return fundDate;
    }

    public void setFundDate(String fundDate) {
        this.fundDate = fundDate;
    }

    public String getFundTime() {
        return fundTime;
    }

    public void setFundTime(String fundTime) {
        this.fundTime = fundTime;
    }

    public String getFundSeqId() {
        return fundSeqId;
    }

    public void setFundSeqId(String fundSeqId) {
        this.fundSeqId = fundSeqId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    @Override
    public String toString() {
        return "AnnounceResponse{" +
                "instuId='" + instuId + '\'' +
                ", fundDate='" + fundDate + '\'' +
                ", fundTime='" + fundTime + '\'' +
                ", fundSeqId='" + fundSeqId + '\'' +
                ", respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                '}';
    }
}
