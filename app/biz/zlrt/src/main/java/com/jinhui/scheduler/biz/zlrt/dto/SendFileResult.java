package com.jinhui.scheduler.biz.zlrt.dto;

/**
 * 赎回报送，监管报送结果列表
 *
 * @autor wsc
 * @create 2018-03-06 15:08
 **/
public class SendFileResult {

    //商户号
    private String instuId;
    //赎回报送内容
    private String redeemSendContent;
    //报送结果
    private String redeemSendResult;
    //监管报送内容
    private String bankSendContent;
    //监管报送结果
    private String bankSendResult;
    //通知证联结果
    private String autoZlResult;

    public String getInstuId() {
        return instuId;
    }

    public void setInstuId(String instuId) {
        this.instuId = instuId;
    }

    public String getRedeemSendContent() {
        return redeemSendContent;
    }

    public void setRedeemSendContent(String redeemSendContent) {
        this.redeemSendContent = redeemSendContent;
    }

    public String getRedeemSendResult() {
        return redeemSendResult;
    }

    public void setRedeemSendResult(String redeemSendResult) {
        this.redeemSendResult = redeemSendResult;
    }

    public String getBankSendContent() {
        return bankSendContent;
    }

    public void setBankSendContent(String bankSendContent) {
        this.bankSendContent = bankSendContent;
    }

    public String getBankSendResult() {
        return bankSendResult;
    }

    public void setBankSendResult(String bankSendResult) {
        this.bankSendResult = bankSendResult;
    }

    public String getAutoZlResult() {
        return autoZlResult;
    }

    public void setAutoZlResult(String autoZlResult) {
        this.autoZlResult = autoZlResult;
    }

    @Override
    public String toString() {
        return "SendFileResult{" +
                "instuId='" + instuId + '\'' +
                ", redeemSendContent='" + redeemSendContent + '\'' +
                ", redeemSendResult='" + redeemSendResult + '\'' +
                ", bankSendContent='" + bankSendContent + '\'' +
                ", bankSendResult='" + bankSendResult + '\'' +
                ", autoZlResult='" + autoZlResult + '\'' +
                '}';
    }
}
