package org.TrueCaller.data.repository;


import org.TrueCaller.data.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, String> {
}
