package com.agh.northwindproject.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController {
    @GetMapping("/api/categories")
    public String getTest() {
        return "categories controller";
    }
}
