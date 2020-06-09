package com.photo.center.repository;

import com.photo.center.domain.admin.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: 用户持久类
 */

@Repository
public interface UserRepository extends JpaRepository<SysUser, Integer> {
    /**
     * 根据用户名获取用户详情信息
     *
     * @param userName
     * @return
     */
    SysUser findByUserName(String userName);



}