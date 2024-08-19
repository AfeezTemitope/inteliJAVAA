package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional <Customer> findByCustomerEmail(String customerEmail);

}
