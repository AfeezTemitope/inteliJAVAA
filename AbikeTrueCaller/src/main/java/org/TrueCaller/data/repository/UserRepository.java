package org.TrueCaller.data.repository;
import org.TrueCaller.data.models.User


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);

}
