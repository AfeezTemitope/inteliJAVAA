package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.Contacts;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contacts, String> {
    List<Contacts> findContactsBy(String userId);

    Optional<Contacts> findByPhoneNumber(String number);

    boolean existsByPhoneNumber(String phoneNumber);
}
