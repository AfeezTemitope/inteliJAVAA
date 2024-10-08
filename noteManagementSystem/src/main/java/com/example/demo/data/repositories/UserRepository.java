package com.example.demo.data.repositories;

import com.example.demo.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findUserByUsername(String username);
}
