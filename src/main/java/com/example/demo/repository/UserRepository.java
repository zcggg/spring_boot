package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.user_id=:id")
    User find(@Param("id") int number);
    @Query("SELECT u FROM User u WHERE u.user_name=:name")
    User findByName(@Param("name") String name);
    @Query("SELECT u FROM User u order by u.user_inv_times desc")
    List<User> findByNameAndTimes(@Param("name") String name);
}