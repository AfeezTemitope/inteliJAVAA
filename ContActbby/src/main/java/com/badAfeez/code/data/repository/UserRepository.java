package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional <User> findByEmail(String email);
}
