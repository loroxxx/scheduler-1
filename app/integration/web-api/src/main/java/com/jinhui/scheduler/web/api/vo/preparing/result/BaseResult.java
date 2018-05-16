package com.jinhui.scheduler.web.api.vo.preparing.result;

import com.jinhui.scheduler.web.api.vo.base.WebResult;

/**
 * Created by jinhui on 2017/7/14.
 */
public class BaseResult<T> extends WebResult {

    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "data=" + data +
                '}';
    }
}
