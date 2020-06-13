package com.photo.center.domain.admin;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: zeng wu
 * @create: 2020-06-08 21:59
 * @description: 用户表
 */
@Data
@Entity
@Table(name = "sys_user")
@ToString
public class SysUser implements Cloneable, Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 用户信息ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id ;

    /**
     * 登录名称
     */
    @Column(name = "username",unique = true)
    private String userName;

    /**
     * 登录密码
     */
    private String password;
    @Column(columnDefinition = "enum(1,0)")
    private int status;// 1:正常状态,0：用户失效.

    private Date lastLoginTime;
    private String imageUrl;
    private int notExpired;
    private int accountNotLocked;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;

    @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
            @JoinColumn(name = "rid") })
    private List<SysRole> roleList;// 一个用户具有多个角色

}
