package com.photo.center.domain.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: zeng wu
 * @create: 2020-06-08 22:11
 * @description: 用户角色关联表
 */
@Data
@Entity
@Table(name = "sys_user_role")
@ToString
public class SysUserRole implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private long uid;
    @Id
    private long rid;
}
