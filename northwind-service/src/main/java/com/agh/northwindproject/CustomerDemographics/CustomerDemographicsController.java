package com.agh.northwindproject.CustomerDemographics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CustomerDemographicsController {
   @Autowired
   private CustomerDemographicsRepository customerDemographicsRepository;

    @GetMapping(value = "/api/customerDemographics")
    @ResponseBody
    public ResponseEntity<List<CustomerDemographic>> getAllCustomerDemographics(){
        return ResponseEntity.ok(customerDemographicsRepository.findAll());
    }

    @PostMapping(value = "/api/customerDemographic")
    @ResponseBody
    public ResponseEntity<String> addNewCustomerDemographic(@RequestBody CustomerDemographic customerDemographic){
        customerDemographicsRepository.save(customerDemographic);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/customerDemographic/{customerDesc}")
    @ResponseBody
    public ResponseEntity<CustomerDemographic> getCustomerDemographicByDescription(@PathVariable String customerDesc){
        return ResponseEntity.ok(customerDemographicsRepository.findByCustomerDesc(customerDesc));
    }

    @DeleteMapping(value = "/api/customerDemographic/{customerDesc}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomerDemographic(@PathVariable String customerDesc){
        CustomerDemographic customerDemographic = customerDemographicsRepository.findByCustomerDesc(customerDesc);
        if(customerDemographic != null){
            customerDemographicsRepository.delete(customerDemographic);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }
}
