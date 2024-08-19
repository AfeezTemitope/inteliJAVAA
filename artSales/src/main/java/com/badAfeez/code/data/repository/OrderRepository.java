package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
