package com.dzg.service;

import com.dzg.repo.UserRepository;
import com.dzg.mmentity.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }
}
