package com.photo.center.service;

import com.photo.center.domain.admin.SysUser;
import com.photo.center.repository.UserRepository;
import com.photo.center.util.MD5Util;
import com.photo.center.util.PageUtil;
import com.photo.center.vo.SysUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: 用户服务类
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserRepository repository;

    private final String imgUrl = "/assets/images/dudu.jpg";

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

    public SysUserVO getUserById(Long id) {
        SysUserVO sysUserVO = new SysUserVO();
        SysUser user = repository.findUserById(id);
        if (user != null) {
            BeanUtils.copyProperties(user, sysUserVO);
        }
        return sysUserVO;
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    public void saveUser(SysUserVO sysUserVO, String name) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        sysUser.setPassword(MD5Util.encode("123456"));
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setCreateUser(name);
        sysUser.setUpdateUser(name);
        if (sysUserVO.getImageUrl() == null || "".equals(sysUserVO.getImageUrl())) {
            sysUser.setImageUrl(imgUrl);
        }
        repository.save(sysUser);
    }

    public void updateUser(SysUserVO sysUserVO, String name) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        if(sysUserVO.getPassword()!=null&&!"".equals(sysUserVO.getPassword())){
            sysUser.setPassword(MD5Util.encode(sysUserVO.getPassword()));
        }
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser(name);
        if (sysUserVO.getImageUrl() == null || "".equals(sysUserVO.getImageUrl())) {
            sysUser.setImageUrl(imgUrl);
        }
        repository.save(sysUser);
    }

    public List<SysUserVO> queryUserList(PageUtil page) {

        // 构造自定义查询条件
        Specification<SysUser> queryCondition = new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                Map condition = page.getCondition();
                if (condition != null && condition.size() > 0) {
                    condition.forEach((k, v) -> {

                        if (k != null && "userName".equals(k) && v != null && !"".equals(v)) {
                            predicateList.add(criteriaBuilder.like(root.get("userName"), "%" + v + "%"));
                        }
                        if (k != null && "createUser".equals(k) && v != null && !"".equals(v)) {
                            predicateList.add(criteriaBuilder.like(root.get("createUser"), "%" + v + "%"));
                        }
                        if (k != null && "status".equals(k) && v != null && !"".equals(v)) {
                            predicateList.add(criteriaBuilder.equal(root.get("status"), v));
                        }
                    });
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        List<SysUser> list = repository.findAll(queryCondition, PageRequest.of(page.getPage() - 1, page.getRows(), Sort.by(Sort.Direction.ASC, "id"))).getContent();
        List<SysUserVO> listVo = new ArrayList();
        list.forEach(user -> {
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtils.copyProperties(user, sysUserVO);
            listVo.add(sysUserVO);
        });
        return listVo;
    }

    public SysUserVO queryUserByName(String name) {
        SysUserVO sysUserVO = new SysUserVO();
        SysUser user = repository.findByUserName(name);
        if (user != null && !"".equals(user)) {
            BeanUtils.copyProperties(user, sysUserVO);
        }
        return sysUserVO;
    }
}