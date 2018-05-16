package com.jinhui.scheduler.web.api.controller.login.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private String unauthenticatedUrl;


    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        /**
         * 开发环境不限制
         */
        String env = System.getProperty("deploy.env");
        if ("dev".equals(env))
            return true;

        //获取请求的URL  
        String url = request.getRequestURI();

        //除了login,swagger是可以公开访问的，其它的URL都进行拦截控制
        if (url.indexOf("admin") >= 0 || url.indexOf("dist") >= 0 || url.indexOf("api-docs") >= 0) {
            return true;
        }


        //获取Session,如果session存在，返回，不存在返回null
        HttpSession session = request.getSession(false);

        boolean online = UserSessionListener.isOnline(session);

        if (online) {
            return true;
        }

        request.getRequestDispatcher(unauthenticatedUrl).forward(request, response);
        return false;

    }

    public void setUnauthenticatedUrl(String unauthenticatedUrl) {
        this.unauthenticatedUrl = unauthenticatedUrl;
    }
}
