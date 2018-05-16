package com.jinhui.scheduler.biz.zlrt.utils.zlrt;



/**
 * 开户请求参数
 *
 * @autor wsc
 * @create 2018-01-30 9:18
 **/
public class AnnounceRequest {

    //证联支付分配给商户的机构代码
    private String instuId;
    //商户的系统日期(YYYYMMDD)
    private String fundDate;
    //商户的时间戳(HHMMSS)
    private String fundTime;
    //商户系统流水号
    private String fundSeqId;

    private String fileName;

    private String filePath;

    private String busiType;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    @Override
    public String toString() {
        return "AnnounceRequest{" +
                "instuId='" + instuId + '\'' +
                ", fundDate='" + fundDate + '\'' +
                ", fundTime='" + fundTime + '\'' +
                ", fundSeqId='" + fundSeqId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", busiType='" + busiType + '\'' +
                '}';
    }
}
