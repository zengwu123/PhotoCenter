package com.photo.center.controller;

import com.photo.center.domain.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: Hello 测试控制类
 */
@Controller
public class LoginController {
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAdmin() {
        return "main";
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    @ResponseBody
    public SysUser getLoginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysUser user = new SysUser();
        user.setId(1);
        user.setUserName("zengwu");
        user.setRoles("admin");
        return user;
    }


    @RequestMapping(value="/learn",method = RequestMethod.GET)
    public String getLearn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "learn-resource";
    }


    @RequestMapping(value="/learn1",method = RequestMethod.GET)
    public String getLearn1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "learn-resource2";
    }
}