package com.dzg.repo;

import com.dzg.mmentity.user.User ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
