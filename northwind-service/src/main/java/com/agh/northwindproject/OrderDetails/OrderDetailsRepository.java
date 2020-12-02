package com.agh.northwindproject.OrderDetails;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, BigInteger> {
}
