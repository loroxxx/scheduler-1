package com.jinhui.scheduler.biz.imiqian.pojo;

/**
 * 导入失败错误列表
 *
 * created by wsc 2017-06-15 9:03
 **/
public class ErrorInfo {

    private String chnCode;

    private String chnName;

    private String errorInfo;

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
