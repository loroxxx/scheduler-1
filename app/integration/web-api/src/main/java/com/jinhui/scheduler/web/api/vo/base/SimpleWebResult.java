package com.jinhui.scheduler.web.api.vo.base;

/**
 * Created by Administrator on 2017/12/11 0011.
 */
public class SimpleWebResult extends WebResult {

    private Object data;

    public static SimpleWebResult ok() {
        SimpleWebResult simpleWebResult = new SimpleWebResult();
        simpleWebResult.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        return simpleWebResult;
    }

    public static SimpleWebResult error() {
        SimpleWebResult simpleWebResult = new SimpleWebResult();
        simpleWebResult.setResultCode(WebConstants.RESULT_FAIL_CODE);
        return simpleWebResult;
    }

    public static SimpleWebResult error(String message) {
        SimpleWebResult simpleWebResult = new SimpleWebResult();
        simpleWebResult.setMessage(message);
        simpleWebResult.setResultCode(WebConstants.RESULT_FAIL_CODE);
        return simpleWebResult;
    }



    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
