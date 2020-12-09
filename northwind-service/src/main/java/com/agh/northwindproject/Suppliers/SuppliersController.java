package com.agh.northwindproject.Suppliers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuppliersController {

    @Autowired
    private SuppliersRepository suppliersRepository;

    @GetMapping(value = "/api/suppliers")
    @ResponseBody
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        return ResponseEntity.ok(suppliersRepository.findAll());
    }

    @PostMapping(value = "/api/supplier")
    @ResponseBody
    public ResponseEntity<String> addNewSupplier(@RequestBody SupplierRequestBody supplierRequestBody){
        suppliersRepository.save(new Supplier(supplierRequestBody));
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/supplier/{companyName}")
    @ResponseBody
    public ResponseEntity<Supplier> getSupplierByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(suppliersRepository.findByCompanyName(companyName));
    }

    @DeleteMapping(value = "/api/supplier/{supplierID}")
    @ResponseBody
    public ResponseEntity<String> deleteSupplier(@PathVariable String supplierID){
        Supplier supplier = suppliersRepository.findById(supplierID).get();
        if(supplier != null){
            suppliersRepository.delete(supplier);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"supplier not existing\"");
    }
}
