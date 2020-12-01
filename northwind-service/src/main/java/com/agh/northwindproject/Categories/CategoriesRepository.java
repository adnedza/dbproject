package com.agh.northwindproject.Categories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigInteger;

public interface CategoriesRepository extends MongoRepository<Category, BigInteger> {
}
