package com.chatapp.app.repository;

import com.chatapp.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    @Query(value = "SELECT * FROM user WHERE user_name =:userName", nativeQuery = true)
    User findUser(@Param("userName") String userName);

}
