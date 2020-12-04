package com.agh.northwindproject.Products;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductsRespository extends MongoRepository<Product, String> {
    List<Product> findAll();
    Product findByProductName(String productName);
}
