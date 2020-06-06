package com.photo.center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: Hello 测试控制类
 */
@Controller
public class HelloController {
    @RequestMapping(value={"/","/index","index.html"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String getHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] c = request.getCookies();
        // Lambda 表达式遍历（JDK 1.8）
        System.out.println("\n第四种方式：Lambda 表达式遍历 Array 数组");
        Arrays.asList(c).forEach(cookie -> System.out.println(cookie.getName()));
        return "index";
    }

    @RequestMapping(value="/toLogin",method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "login";
    }

    @RequestMapping(value="/user",method = RequestMethod.GET)
    public String getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "admin/user";
    }

    @RequestMapping(value="/403",method = RequestMethod.GET)
    public String get403(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/403";
    }
}