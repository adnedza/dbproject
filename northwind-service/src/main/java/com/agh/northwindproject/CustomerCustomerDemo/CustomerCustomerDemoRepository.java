package com.agh.northwindproject.CustomerCustomerDemo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface CustomerCustomerDemoRepository extends MongoRepository<CustomerCustomerDemo, BigInteger> {
}
