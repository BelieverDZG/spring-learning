package com.dzg.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JpaTest {

    @Autowired
    UsersRepository usersRepo;

    @Test
    public void findAllTest() {
        List<Users> usersList = usersRepo.findAll();
//        usersRepo.findById(2);
        if (!usersList.isEmpty()) {
            usersList.stream().forEach(user -> System.out.println(user));
        }
    }


    @Test
    public void findByTest() {
        /*Users usersByIdAfter = usersRepo.findUsersByIdAfter(1);
        if (usersByIdAfter!=null){
            System.out.println(usersByIdAfter.toString());
        }*/

        List<Users> users = usersRepo.findAllByIdGreaterThan(0);
        if (!users.isEmpty()) {
            users.stream().forEach(user -> System.out.println(user));
        }
    }
    
    
    @Test
    public void insertTest() {
        Users user = new Users();
        user.setId(4);
        user.setUserName("李四");
        user.setPassword("678910");
        Users save = usersRepo.save(user);
        if (save != null) System.out.println("增加用户成功");
    }
}
