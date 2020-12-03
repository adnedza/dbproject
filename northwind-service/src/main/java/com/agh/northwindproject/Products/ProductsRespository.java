package com.agh.northwindproject.Products;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProductsRespository extends MongoRepository<Product, BigInteger> {
    List<Product> findAll();
    Product findByProductName(String productName);
}
