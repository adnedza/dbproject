package com.agh.northwindproject.Categories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoriesRepository extends MongoRepository<Category, String> {
    List<Category> findAll();
    Category findByCategoryName(String categoryName);
}
