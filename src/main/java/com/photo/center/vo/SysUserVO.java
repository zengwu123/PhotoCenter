package com.photo.center.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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

    private Date lastLoginTime;
    private String imageUrl;
    private int notExpired;
    private int accountNotLocked;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;
}
