package com.photo.center.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: zengwu
 * @Date: 2020/5/17 8:50
 * @Description:
 */
@ControllerAdvice
public class HandleControllerException {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        if (e instanceof AccessDeniedException) {
            //如果是权限不足异常，则跳转到权限不足页面
            return "/error/403";
        }
        //其余异常都到500页面
        return "/500";
    }
}
