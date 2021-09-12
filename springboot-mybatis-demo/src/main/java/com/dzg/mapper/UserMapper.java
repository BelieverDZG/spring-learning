package com.dzg.mapper;

import com.dzg.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository jpa添加的，mybatis不用
public interface UserMapper {


    int addUser(@Param("user") User user);

    int deleteUserById(int id);

    int updateUserById(@Param("user") User user);

    User findUserById(int id);

    List<User> findUserByIds(List<Integer> ids);

    //    @Select(value = "select * from user")
    List<User> getAllUsers();
}
