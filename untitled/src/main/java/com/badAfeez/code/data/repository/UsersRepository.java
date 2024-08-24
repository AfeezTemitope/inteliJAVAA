package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByEmail(String email);
}
