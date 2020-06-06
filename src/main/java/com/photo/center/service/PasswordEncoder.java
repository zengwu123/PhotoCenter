package com.photo.center.service;

import com.photo.center.util.MD5Util;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: 密码加密类
 */
public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encode((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {//admin Details Service验证
        return encodedPassword.equals(MD5Util.encode((String) rawPassword));
    }

}