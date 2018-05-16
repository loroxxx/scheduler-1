package com.jinhui.scheduler.web.api.controller.login.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jinhui on 2017/7/12.
 */
public class UserSessionListener implements HttpSessionListener {
    private final static Logger logger = LoggerFactory.getLogger(UserSessionListener.class);

    private static HashMap hUserName = new HashMap();//保存sessionID和username的映射


    public void sessionCreated(HttpSessionEvent se) {

    }

    public void sessionDestroyed(HttpSessionEvent se) {

        logger.info("session: {} is destroy", se.getSession().getId());
        hUserName.remove(se.getSession().getId());
    }


    public static void saveUserSession(HttpSession session, String sUserName) {


        removeUserSession(sUserName);


        logger.info("添加保存sessionID和username的映射:{}", sUserName);
        synchronized (hUserName) {
            hUserName.put(session.getId(), sUserName);
        }


    }


    /**
     * 查看该用户的Session是否已经登陆过,true为已经登录
     *
     * @param session
     * @return
     */
    public static boolean isOnline(HttpSession session) {
        boolean flag = false;

        if (session == null) {
            return flag;
        }

        if (hUserName.containsKey(session.getId())) {

            flag = true;
        }
        return flag;
    }

    public static void removeUserSession(String sUserName) {

        if (hUserName.containsValue(sUserName)) {
            //遍历原来的hUserName，删除原用户名对应的sessionID
            Iterator iter = hUserName.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                if (((String) val).equals(sUserName)) {
                    logger.info("移除{}的session", sUserName);
                    synchronized (hUserName) {
                        hUserName.remove(key);
                    }

                }
            }

        }
    }

}
