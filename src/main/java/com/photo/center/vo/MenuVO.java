package com.photo.center.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: zeng wu
 * @create: 2020-06-16 21:55
 * @description: 菜单明细
 */
@Data
public class MenuVO<T> {


    private long id;// 主键.

    private String permissionName;// 名称.

    private String permissionUrl;// 资源路径.

    private List<T> children; // 子菜单

    private int orderNum; //排序编号


}
