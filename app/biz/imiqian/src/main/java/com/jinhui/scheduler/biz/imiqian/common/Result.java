package com.jinhui.scheduler.biz.imiqian.common;

/**
 * 返回前端错误信息
 * create by wsc 2017-06-07 14:30
 * @param <T>
 */
public class Result<T> {

    private int status;
    private String msg;
    private T data;

    public static <T> Result<T> successData(T t) {
        Result<T> result = new Result<>();
        result.status = Status.SUCCESS.getStatus();
        result.msg = Status.SUCCESS.getMsg();
        result.data = t;
        return result;
    }

    public static <T> Result<T> errorData(T t,String msg) {
        Result<T> result = new Result<>();
        result.status = Status.ERROR.getStatus();
        result.msg = msg;
        result.data = t;
        return result;
    }

    public static Result<Void> success(String msg) {
        Result<Void> result = new Result<>();
        result.status = Status.SUCCESS.getStatus();
        result.msg = msg;
        return result;
    }

    public static Result<Void> success() {
        Result<Void> result = new Result<>();
        result.status = Status.SUCCESS.getStatus();
        result.msg = Status.SUCCESS.getMsg();
        return result;
    }

    public static Result<Void> fail() {
        Result<Void> result = new Result<>();
        result.status = Status.FAIL.getStatus();
        result.msg = Status.FAIL.getMsg();
        return result;
    }

    public static Result<Void> fail(String msg) {
        Result<Void> result = new Result<>();
        result.status = Status.FAIL.getStatus();
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> failData(T t) {
        Result<T> result = new Result<>();
        result.status = Status.FAIL.getStatus();
        result.msg = Status.FAIL.getMsg();
        result.data = t;
        return result;
    }

    private Result(){}

    public Result(Status status){
        this.status = status.getStatus();
        this.msg = status.getMsg();
    }

    public void setError(Status status){
        this.status = status.getStatus();
        this.msg = status.getMsg();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS.getStatus();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
