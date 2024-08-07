package com.example.restaurant.oldies.repository;

import com.example.restaurant.oldies.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select usr from User usr where usr.id = :userId")
    User findUserById(@Param("userId") long userId);

    User findByEmailOrUsername(String email, String username);

    @Query(value = "select usr from User usr where usr.username = :username and usr.password = :password")
    User checkUserLoginCredentials(@Param(value = "username") String username, @Param(value = "password") String password);
}
