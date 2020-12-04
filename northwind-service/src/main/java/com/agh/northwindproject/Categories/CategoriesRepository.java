package com.agh.northwindproject.Categories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface CategoriesRepository extends MongoRepository<Category, BigInteger> {
    List<Category> findAll();
    Category findByCategoryName(String categoryName);
}
