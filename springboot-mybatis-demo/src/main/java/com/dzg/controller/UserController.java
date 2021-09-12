package com.dzg.controller;

import com.dzg.entity.User;
import com.dzg.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getById/{id}")
    public String getUserById(@PathVariable(name = "id") String id) {
        return userService.findUserById(Integer.parseInt(id)).toString();
    }

    @GetMapping(value = "/getUserByIds")
    public String getUserByIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<User> userByIds = userService.findUserByIds(ids);
        if (userByIds != null) {
            return userByIds.toString();
        } else {
            return "查询失败";
        }
    }

    @GetMapping(value = "/getAllUsers")
    public String getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return Optional.ofNullable(allUsers).orElse(null).toString();
    }


    @GetMapping(value = "/add")
    public String addUser() {
        User user = new User();
        user.setId(4);
        user.setUserName("新增用户");
        user.setPassword("newuser");
        int res = userService.addUser(user);
        return  res > 0 ? "新增用户成功" : "添加用户失败";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        return userService.deleteUserById(id) > 0 ? "删除成功" : "删除失败";
    }

    @GetMapping(value = "/update/{id}")
    public String updateUser(@PathVariable(name = "id") int id) {
        User user = new User();
        user.setId(id);
        user.setUserName("update用户");
//        user.setPassword("updateuser");
        return userService.updateUserById(user) > 0 ? "修改用户成功" : "修改用户失败";
    }
}
