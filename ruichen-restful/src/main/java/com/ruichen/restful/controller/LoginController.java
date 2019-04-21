package com.ruichen.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/")
    public String login(){
        return "login";
    }
}
