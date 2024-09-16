package com.devteria.course_spring_security.repository;

import com.devteria.course_spring_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    boolean existsByUserName(String username);
    Optional<User> findByUserName(String userName);
}
