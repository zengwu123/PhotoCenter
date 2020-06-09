package com.photo.center.service;

import com.photo.center.domain.admin.SysUser;
import com.photo.center.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: 用户服务类
 */
@Service
public class UserService<T extends SysUser> implements UserDetailsService {

    @Resource
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SysUser user = repository.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            //用户权限
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            user.getRoleList().forEach(sysRole -> {
                if (StringUtils.isNotBlank(sysRole.getRoleName())) {
                    authorities.add(new SimpleGrantedAuthority(sysRole.getRoleName().trim()));
                }
            });

            return new User(user.getUserName(), user.getPassword(), authorities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SysUser getUser(String username) {
        return repository.findByUserName(username);
    }
}