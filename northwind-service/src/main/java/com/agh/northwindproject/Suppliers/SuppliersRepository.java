package com.agh.northwindproject.Suppliers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SuppliersRepository extends MongoRepository<Supplier, BigInteger> {
}
