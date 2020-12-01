package com.agh.northwindproject.Customers;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface CustomersRepository extends MongoRepository<Customer, BigInteger> {
}
