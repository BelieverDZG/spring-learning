package com.dzg.bean;

import org.springframework.context.annotation.Bean;

/**
 * 没有Spring注解
 */
public class UserConfiguration {

    @Bean
    public User getUser() {
        User user = new User();
        user.setAge(18);
        user.setName("dengzhiguo");
        return user;
    }
}
