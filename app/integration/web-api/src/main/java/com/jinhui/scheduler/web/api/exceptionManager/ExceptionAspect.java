package com.jinhui.scheduler.web.api.exceptionManager;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
@Aspect
public class ExceptionAspect {

    private static Logger logger = null;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody))")
    public void annotationPointcut(){}

    @Around("annotationPointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
            if(logger.isWarnEnabled()) {
                logger.warn("请求后台接口, 未捕获异常: {}", e);
            }
        } catch (Throwable throwable) {
            logger = LoggerFactory.getLogger(ExceptionAspect.class);
            logger.error("aop异常处理发生异常: {}",throwable);
        }
        return null;
    }

}
