package com.photo.center.controller;

import com.photo.center.service.UserService;
import com.photo.center.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: Hello 测试控制类
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/queryMenuList", method = RequestMethod.POST)
    @ResponseBody
    public List<MenuVO> getLoginOut(Authentication authentication) throws Exception {
        List<MenuVO> menuVOS = userService.queryMenuList(authentication.getName());
        return menuVOS;
    }


    @RequestMapping(value = "/learn", method = RequestMethod.GET)
    public String getLearn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "learn-resource";
    }


    @RequestMapping(value = "/learn1", method = RequestMethod.GET)
    public String getLearn1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "learn-resource2";
    }
}