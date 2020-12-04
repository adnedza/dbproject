package com.agh.northwindproject.CustomerCustomerDemo;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographicsRepository;
import com.agh.northwindproject.Customers.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerCustomerDemoController {
    @Autowired
    private CustomerDemographicsRepository customerDemographicsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomerCustomerDemoRepository customerCustomerDemoRepository;

    @GetMapping(value = "/api/customerCustomerDemo")
    @ResponseBody
    public ResponseEntity<List<CustomerCustomerDemo>> getAllCustomersCustomerDemo(){
        return ResponseEntity.ok(customerCustomerDemoRepository.findAll());
    }

    @PostMapping(value = "/api/customerCustomerDemo")
    @ResponseBody
    public ResponseEntity<String> addNewCustomerCustomerDemo(@RequestBody CustomerCustomerDemoRequestBody customerCustomerDemoRequestBody){
        CustomerCustomerDemo customerCustomerDemo = new CustomerCustomerDemo();

        customerCustomerDemo.setCustomer(customersRepository.findByCompanyName(
                customerCustomerDemoRequestBody.getCompanyName()));
        customerCustomerDemo.setCustomerDemographic(customerDemographicsRepository.findByCustomerDesc(
                customerCustomerDemoRequestBody.getCustomerDesc()));

        customerCustomerDemoRepository.save(customerCustomerDemo);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/customersByCustomerDemographic/{customerDesc}")
    @ResponseBody
    public ResponseEntity<List<CustomerCustomerDemo>> getCustomersByCustomerDemographic(@PathVariable String customerDesc){
        return ResponseEntity.ok(customerCustomerDemoRepository.findByCustomerDemographic(
                customerDemographicsRepository.findByCustomerDesc(customerDesc)));
    }

    @GetMapping(value = "/api/customerCustomerDemo/{companyName}")
    @ResponseBody
    public ResponseEntity<CustomerCustomerDemo> getCustomerDemographic(String companyName){
        return ResponseEntity.ok(customerCustomerDemoRepository.findByCustomer(
                customersRepository.findByCompanyName(companyName)));
    }

    @DeleteMapping(value = "/api/customerCustomerDemo/{companyName}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomerCustomerDemo(@PathVariable String companyName){
        CustomerCustomerDemo customerCustomerDemo = customerCustomerDemoRepository.
                findByCustomer(customersRepository.findByCompanyName(companyName));
        if(customerCustomerDemo != null){
            customerCustomerDemoRepository.delete(customerCustomerDemo);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"product already not existing, cannot remove\"");
    }
}
