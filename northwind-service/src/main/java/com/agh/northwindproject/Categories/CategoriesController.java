package com.agh.northwindproject.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CategoriesController {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping(value = "/api/categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoriesRepository.findAll());
    }

    @PostMapping(value = "/api/category")
    @ResponseBody
    public ResponseEntity<String> addNewCategory(@RequestBody CategoryRequestBody categoryRequestBody){
        categoriesRepository.save(new Category(categoryRequestBody));
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/category/{categoryName}")
    @ResponseBody
    public ResponseEntity<Category> getCategoryByCategoryName(@PathVariable String categoryName){
        return ResponseEntity.ok(categoriesRepository.findByCategoryName(categoryName));
    }

    @DeleteMapping(value = "/api/category/{categoryID}")
    @ResponseBody
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryID){
        Category category = categoriesRepository.findById(categoryID).get();
        if(category != null){
            categoriesRepository.delete(category);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }
}
