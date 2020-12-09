package com.agh.northwindproject.CustomerCustomerDemo;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographic;
import com.agh.northwindproject.CustomerDemographics.CustomerDemographicsRepository;
import com.agh.northwindproject.Customers.Customer;
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

    @PostMapping(value = "/api/customerCustomerDemo/{customerID}/{customerDemographicsID}")
    @ResponseBody
    public ResponseEntity<String> addNewCustomerCustomerDemo(@PathVariable String customerID,
                                                             @PathVariable String customerDemographicsID){
        Customer customer = customersRepository.findById(customerID).get();
        if(customer != null) {
            CustomerDemographic customerDemographic = customerDemographicsRepository.findById(customerDemographicsID).get();
            if (customerDemographic != null) {
                CustomerCustomerDemo customerCustomerDemo = new CustomerCustomerDemo(customerDemographic);
                customer.getCustomerCustomerDemo().add(customerCustomerDemo);
                customerCustomerDemoRepository.save(customerCustomerDemo);
                customersRepository.save(customer);
                return ResponseEntity.ok("\"status\": \"added\"");
            }
            return ResponseEntity.ok("\"status\": \"customerDemographic not existing\"");
        }
        return ResponseEntity.ok("\"status\": \"customer not existing\"");
    }

    @GetMapping(value = "/api/customersByCustomerDemographic/{customerID}")
    @ResponseBody
    public ResponseEntity<List<CustomerCustomerDemo>> getCustomerDemographics(@PathVariable String customerID){
        return ResponseEntity.ok(customersRepository.findById(customerID).get().getCustomerCustomerDemo());
    }

    @DeleteMapping(value = "/api/customerCustomerDemo/{companyName}/{customerCustomerDemoID}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomerCustomerDemo(@PathVariable String companyName,
                                                             @PathVariable String customerCustomerDemoID) {
        Customer customer = customersRepository.findByCompanyName(companyName);

        if (customer != null) {
            CustomerCustomerDemo customerCustomerDemo = customerCustomerDemoRepository
                    .findById(customerCustomerDemoID).get();
            if (customerCustomerDemo != null) {
                customerCustomerDemoRepository.delete(customerCustomerDemo);
                return ResponseEntity.ok("\"status\": \"removed\"");
            }
            return ResponseEntity.ok("\"status\": \"customerCustomerDemo not existing\"");
        }
        return ResponseEntity.ok("\"status\": \"customer not existing\"");
    }
}
