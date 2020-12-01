package com.agh.northwindproject.Shippers;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface ShippersRepository extends MongoRepository<Shipper, BigInteger> {
}
