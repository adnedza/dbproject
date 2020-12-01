package com.agh.northwindproject.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    @GetMapping("/api/products")
    public String getTest() {
        return "products controller";
    }

}
