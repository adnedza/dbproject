package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.CategoriesRepository;
import com.agh.northwindproject.Suppliers.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
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
    public ResponseEntity<String> addNewProduct(@RequestBody ProductsRequestBody productsRequestBody){
        Product product = new Product(productsRequestBody);
        product.setCategory(categoriesRepository.findById(productsRequestBody.getCategoryID()).get());
        product.setSupplierID(suppliersRepository.findById(productsRequestBody.getSupplierID()).get().getId());
        productsRespository.save(product);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/product/{productName}")
    @ResponseBody
    public ResponseEntity<Product> getProductByProductName(@PathVariable String productName){
        return ResponseEntity.ok(productsRespository.findByProductName(productName));
    }

    @GetMapping(value = "/api/product/id/{productID}")
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable String productID){
        return ResponseEntity.ok(productsRespository.findById(productID));
    }

    @DeleteMapping(value = "/api/product/{productID}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable String productID){
        Product product = productsRespository.findById(productID).get();
        if(product != null){
            productsRespository.delete(product);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"product already not existing, cannot remove\"");
    }

}
