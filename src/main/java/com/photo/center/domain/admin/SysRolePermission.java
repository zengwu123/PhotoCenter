package com.photo.center.domain.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: zeng wu
 * @create: 2020-06-08 22:46
 * @description: 角色权限关联表
 */
@Data
@Entity
@Table(name = "sys_role_permission")
@ToString
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long rid;
    @Id
    private long pid;

}
