package com.jinhui.scheduler.web.api.exception;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.web.api.vo.base.SimpleWebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理，捕获所有Controller中抛出的异常。
 * Created by luoyuanq on 2017/9/28 0028.
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //处理BizException业务异常
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public SimpleWebResult customHandler(BizException e) {
        logger.error(e.getMessage(), e);
        SimpleWebResult result = SimpleWebResult.error(e.getMessage());
        return result;
    }

    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e) {

        logger.error(e.getMessage(), e);
        SimpleWebResult result = new SimpleWebResult();
        result.setMessage("未知异常，请联系管理员");
        result.setErrorCode("500");

        //TODO 生产环境下不把具体错误传给前端
        result.setData(e.getMessage());

        return result;
    }


}
