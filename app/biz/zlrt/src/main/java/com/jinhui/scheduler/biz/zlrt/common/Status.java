package com.jinhui.scheduler.biz.zlrt.common;

/**
 * 状态码
 * create by wsc 2017-06-07 14:30
 */
public enum Status {

    //和前端交互的错误码
    SUCCESS(0, "SUCCESS"),
    FAIL(1, "FAIL"),
    ERROR(2, "ERROR"),;

    private int status;
    private String msg;

    private Status(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }
}