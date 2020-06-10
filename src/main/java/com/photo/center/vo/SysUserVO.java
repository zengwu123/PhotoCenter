package com.photo.center.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: zeng wu
 * @create: 2020-06-10 21:45
 * @description: 用户VO
 */
@Data
@ToString
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;

    /**
     * 登录名称
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    private int status;// 1:正常状态,0：用户失效.
}
