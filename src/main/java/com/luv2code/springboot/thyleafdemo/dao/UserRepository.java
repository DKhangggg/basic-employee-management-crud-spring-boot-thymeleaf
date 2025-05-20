package com.luv2code.springboot.thyleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luv2code.springboot.thyleafdemo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
