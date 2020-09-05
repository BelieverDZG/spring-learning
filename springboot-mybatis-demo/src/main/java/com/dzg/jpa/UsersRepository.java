package com.dzg.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 映射的实体类
 * 实体类主键的类型
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    Users findUsersByIdAfter(int id);

    List<Users> findAllByIdGreaterThan(int id);


 /*   @Query("YOUR SQL ")
    List<Users> definitionQuery();*/
}
