package com.photo.center.domain.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zeng wu
 * @create: 2020-06-08 22:35
 * @description: 用户权限表
 */
@Data
@Entity
@Table(name = "sys_permission")
@ToString
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;// 主键.

    @Column(name = "permission_name", unique = true)
    private String permissionName;// 名称.

    //  @Column(columnDefinition = "enum('menu','button')")
    //  private String resourceType;// 资源类型，[menu|button]
    @Column(name = "permission_url", unique = true)
    private String permissionUrl;// 资源路径.

//    private String permission; // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    @Column(name = "parent_id")
    private Long parentId; // 父编号

    private int orderNum; //排序编号
//    private Boolean available = Boolean.FALSE;

}
