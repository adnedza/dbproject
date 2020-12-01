package com.agh.northwindproject.Products;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface ProductsRespository extends MongoRepository<Product, BigInteger> {
}
