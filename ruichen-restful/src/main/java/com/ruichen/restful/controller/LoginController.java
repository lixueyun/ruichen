package com.ruichen.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName  LoginController
 * @Description
 * @Date  2019/4/4 16:06
 * @author  lixueyun
 */
@Controller
public class LoginController {


    /**
     * @methodName  index
     * @description 跳转首页
     * @param
     * @author  lixueyun
     * @Date  2019/4/19 17:20
     * @return  java.lang.String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }

    /**
     * @methodName  logon
     * @description  注册
     * @param
     * @author  lixueyun
     * @Date  2019/6/1 0:40
     * @return  java.lang.String
     */
    @RequestMapping(value = "/logon", method = RequestMethod.GET)
    public String logon(){
        return "logon";
    }

    /**
     * @methodName  findPassword
     * @description  找回密码
     * @param
     * @author  lixueyun
     * @Date  2019/6/1 0:40
     * @return  java.lang.String
     */
    @RequestMapping(value = "findPassword", method = RequestMethod.GET)
    public String findPassword(){
        return "find_password";
    }
}
