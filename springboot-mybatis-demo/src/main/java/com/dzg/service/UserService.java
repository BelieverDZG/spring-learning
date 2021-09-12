package com.dzg.service;

import com.dzg.entity.User;
import com.dzg.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> findUserByIds(List<Integer> ids) {
        return userMapper.findUserByIds(ids);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }
}
