package com.agh.northwindproject.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomersController {
    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping(value = "/api/customers")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customersRepository.findAll());
    }

    @PostMapping(value = "/api/customer")
    @ResponseBody
    public ResponseEntity<String> addNewCustomer(@RequestBody Customer customer){
        customersRepository.save(customer);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/customer/{companyName}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(customersRepository.findByCompanyName(companyName));
    }

    @DeleteMapping(value = "/api/customer/{companyName}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable String companyName){
        Customer customer = customersRepository.findByCompanyName(companyName);
        if(customer != null){
            customersRepository.delete(customer);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }
}
