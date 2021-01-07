package com.agh.northwindproject.Customers;

import com.agh.northwindproject.CustomerCustomerDemo.CustomerCustomerDemo;
import com.agh.northwindproject.CustomerCustomerDemo.CustomerCustomerDemoRepository;
import com.agh.northwindproject.CustomerDemographics.CustomerDemographicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CustomersController {
    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomerDemographicsRepository customerDemographicsRepository;

    @Autowired
    private CustomerCustomerDemoRepository customerCustomerDemoRepository;

    @GetMapping(value = "/api/customers")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customersRepository.findAll());
    }

    @PostMapping(value = "/api/customer")
    @ResponseBody
    public ResponseEntity<String> addNewCustomer(@RequestBody CustomerRequestBody customerRequestBody){
        Customer customer = new Customer(customerRequestBody);
        for (String customerDesc : customerRequestBody.getCustomerCustomerDemo()) {
            CustomerCustomerDemo customerCustomerDemo = new CustomerCustomerDemo(
                    customerDemographicsRepository.findByCustomerDesc(customerDesc));
            customer.getCustomerCustomerDemo().add(customerCustomerDemo);
            customerCustomerDemoRepository.save(customerCustomerDemo);
        }
        customersRepository.save(customer);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/customer/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customersRepository.findById(id));
    }

    @GetMapping(value = "/api/customer/{companyName}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(customersRepository.findByCompanyName(companyName));
    }

    @DeleteMapping(value = "/api/customer/{customerID}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerID){
        Customer customer = customersRepository.findById(customerID).get();
        if(customer != null){
            customersRepository.delete(customer);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"customer not existing\"");
    }
}
