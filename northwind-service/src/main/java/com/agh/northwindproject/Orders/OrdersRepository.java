package com.agh.northwindproject.Orders;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface OrdersRepository extends MongoRepository<Order, BigInteger> {
}
