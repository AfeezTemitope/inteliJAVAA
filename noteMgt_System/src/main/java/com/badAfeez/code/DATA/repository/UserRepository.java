package com.badAfeez.code.DATA.repository;

import com.badAfeez.code.DATA.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
