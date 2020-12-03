package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.CategoriesRepository;
import com.agh.northwindproject.Suppliers.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsRespository productsRespository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping(value = "/api/products")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productsRespository.findAll());
    }

    @PostMapping(value = "/api/product")
    @ResponseBody
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        product.setCategory(categoriesRepository.findByCategoryName(product.getCategory().getCategoryName()));
        product.setSupplier(suppliersRepository.findByCompanyName(product.getSupplier().getCompanyName()));
        productsRespository.save(product);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/product/{productName}")
    @ResponseBody
    public ResponseEntity<Product> getProductByProductName(@PathVariable String productName){
        return ResponseEntity.ok(productsRespository.findByProductName(productName));
    }

    @DeleteMapping(value = "/api/product/{productName}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable String productName){
        Product product = productsRespository.findByProductName(productName);
        if(product != null){
            productsRespository.delete(product);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"product already not existing, cannot remove\"");
    }

}
