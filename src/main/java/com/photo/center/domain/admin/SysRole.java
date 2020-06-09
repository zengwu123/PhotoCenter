package com.photo.center.domain.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: zeng wu
 * @create: 2020-06-08 22:28
 * @description: 角色表
 */
@Data
@Entity
@Table(name = "sys_role")
@ToString
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id; // 编号
    @Column(name = "role_name",unique = true)
    private String roleName; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    @Column(name="role_desc")
    private String roleDesc; // 角色描述,UI界面显示使用
//    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    // 角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns = {
            @JoinColumn(name = "pid") })
    private List<SysPermission> permissions;

}
