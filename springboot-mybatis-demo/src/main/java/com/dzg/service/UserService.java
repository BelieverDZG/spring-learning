package com.dzg.service;

import com.dzg.entity.User;
import com.dzg.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

   /* public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }*/

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }
}
