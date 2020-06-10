package com.photo.center.config;

import com.photo.center.domain.admin.SysUser;
import com.photo.center.service.PasswordEncoder;
import com.photo.center.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //允许基于HttpServletRequest使用限制访问
        http.authorizeRequests()
                //不需要身份认证
                .antMatchers( "/toLogin", "/login").permitAll()
                .antMatchers("/assets/**").permitAll()

                //权限配置
                .antMatchers("/","/index").hasAnyRole("ADMIN","USER")
                .antMatchers("/learn/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/learn1/**").hasRole("ADMIN")
                .anyRequest().authenticated()

                //自定义登录界面
                .and().formLogin().loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/toLogin?error").permitAll()
                .and().exceptionHandling().accessDeniedPage("/toLogin?deny")
                .and().httpBasic()
                .and().sessionManagement().invalidSessionUrl("/toLogin")
                .and().csrf().disable();

        //开启注销操作，并定制注销跳转页面
        http.logout()
                .logoutSuccessUrl("/toLogin")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .deleteCookies();

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder());
    }
}