package com.dzg.mapper;

import com.dzg.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findUserById(int id);
}
