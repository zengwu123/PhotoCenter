package com.photo.center.repository;

import com.photo.center.domain.admin.SysUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: 用户持久类
 */

@Repository
public interface UserRepository extends JpaRepository<SysUser, Long> , JpaSpecificationExecutor<SysUser> {
    /**
     * 根据用户名获取用户详情信息
     *
     * @param userName
     * @return
     */
    SysUser findByUserName(String userName);

    @Query("select s from SysUser s where s.id=:id")
    SysUser findUserById(@Param(value = "id") Long id);

    @Query("delete from SysUser s where s.id=:id")
    @Modifying
    void deleteById(@Param(value = "id") Long id);

}