package org.TrueCaller.data.repository;

import org.TrueCaller.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
