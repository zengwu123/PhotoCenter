package com.photo.center.controller;

import com.github.pagehelper.PageInfo;
import com.photo.center.service.UserService;
import com.photo.center.util.PageUtil;
import com.photo.center.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zeng wu
 * @create: 2020-06-06 23:43
 * @description: 管理员控制类
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user")
    public String toUserPage() {
        return "/admin/user";
    }

    @RequestMapping("/role")
    public String toRolePage() {
        return "/admin/role";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map saveUser(@RequestBody SysUserVO sysUserVO, Authentication authentication) {

        Map map = new HashMap();
        userService.saveUser(sysUserVO, authentication.getName());
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map updateUser(@RequestBody SysUserVO sysUserVO, Authentication authentication) {

        Map map = new HashMap();
        userService.updateUser(sysUserVO, authentication.getName());
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map deleteUser(@RequestBody Long[] ids) {

        Map map = new HashMap();
        for (int i = 0; i < ids.length; i++) {
            userService.deleteUserById(ids[i]);
        }
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/queryUserList")
    @ResponseBody
    public Map queryUserList(PageUtil page) {
        List<SysUserVO> list = userService.queryUserList(page);
        PageInfo<SysUserVO> pageInfo = new PageInfo<SysUserVO>(list);
        Map map = new HashMap();
        map.put("page", pageInfo);
        return map;
    }

    @RequestMapping("/queryUserByName")
    @ResponseBody
    public SysUserVO queryUserByName(Authentication authentication) {
        SysUserVO sysUserVO = userService.queryUserByName(authentication.getName());
        return sysUserVO;
    }
}
