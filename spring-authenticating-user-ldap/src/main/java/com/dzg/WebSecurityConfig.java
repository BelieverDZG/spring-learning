package com.dzg;

import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.support.LdapEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin();//启用默认的登录页面
    }

    /*

    o：organization（组织-公司）
    ou：organization unit（组织单元-部门）
    c：countryName（国家）
    dc：domainComponent（域名）
    sn：suer name（真实名称）
    cn：common name（常用名称）

     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                    .url("ldap://localhost:8389/dc=springframework,dc=org")
//                .ldif("classpath:test-server.ldif")
                    .and()
                .passwordCompare()
                        .passwordEncoder(new LdapShaPasswordEncoder())
                    .passwordAttribute("userPassword");
    }
}
