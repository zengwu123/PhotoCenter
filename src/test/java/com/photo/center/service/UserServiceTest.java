package com.photo.center.service;

import com.photo.center.domain.admin.SysUser;
import com.photo.center.vo.SysUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: zeng wu
 * @create: 2020-06-09 19:38
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        SysUserVO user = userService.getUserById(1L);
        System.out.println(user);
    }
}