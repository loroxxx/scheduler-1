package com.jinhui.scheduler.web.api.controller.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.data.core.mapper.core.AdminMapper;
import com.jinhui.scheduler.domain.base.Admin;
import com.jinhui.scheduler.web.api.controller.login.interceptor.UserSessionListener;
import com.jinhui.scheduler.web.api.vo.preparing.result.LoginResult;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jinhui.scheduler.biz.core.util.UtilTool;

/**
 * 登录
 *
 * @author jinhui
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AdminMapper adminMapper;


    /**
     * 用户登录，在UserSessionListener中维护了一个HashMap，保存所有登录用户的信息
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResult login(@RequestBody Admin user, HttpServletRequest request, HttpServletResponse response) {

        logger.info("登录参数："+user.toString());

        LoginResult result = new LoginResult();
        try {
            user.setPassword(UtilTool.md5Tool(user.getPassword()));
            Admin admin = adminMapper.selectAdmin(user);
            if (admin == null) {
                result.setResultCode("2");
                result.setMessage("用户登录失败!!");

                return result;
            }

            //保存用户信息
            HttpSession session = request.getSession();
            String id = session.getId();
            System.out.println("id:"+id);
            UserSessionListener.saveUserSession(session, admin.getUsername());

            result.setResultCode("0");
            result.setMessage("用户登录成功!!");

            logger.info("{}登陆成功",user.getUsername());
            return result;

        } catch (Exception e) {
            logger.error("【登录login】异常：" + e);
            result.setResultCode("1");
            result.setMessage("【登录login】异常!!");
            return result;
        }
    }

    /**
     * 根据UserName查看用户的登录状态
     */
    @RequestMapping(value ="/queryUserState" ,method = RequestMethod.GET)
    @ResponseBody
    public LoginResult queryUserState(@Param("userName") String userName,  HttpServletRequest request ){

        LoginResult result = new LoginResult();
        boolean online = UserSessionListener.isOnline(request.getSession(false));
        if(online){
            result.setResultCode("0");
            result.setMessage(userName+"登陆状态为成功");
        }else {
            result.setResultCode("1");
            result.setMessage(userName+"登录状态失效");

        }
        return result;

    }




    /**
     * 注销用户
     */
    @RequestMapping(value ="/exitUser" ,method = RequestMethod.GET)
	@ResponseBody
	public LoginResult exitUser(@RequestParam("userName") String userName ){
        logger.info("注销参数："+userName);
        LoginResult result = new LoginResult();

		try {

            UserSessionListener.removeUserSession(userName);
            result.setResultCode("0");
            result.setMessage("注销用户成功");
            logger.info("{}注销成功",userName);
            return result;

		} catch (Exception e) {
            logger.error("注销用户异常："+e);
            result.setResultCode("1");
            result.setMessage("注销用户异常");
            return result;

		}
	}



    @RequestMapping(value="/msgErrorInfo", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
    @ResponseBody
    public String  msgErrorInfo() {
        LoginResult result = new LoginResult();
        result.setErrorCode("9");
        result.setMessage("请求被拦截，用户未登录");
        return JSON.toJSONString(result);
    }


    public static void main(String[] args) {
        String admin = UtilTool.md5Tool("admin3");
        System.out.println(admin);
    }

}
