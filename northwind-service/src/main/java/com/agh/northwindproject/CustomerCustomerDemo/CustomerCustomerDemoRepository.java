package com.agh.northwindproject.CustomerCustomerDemo;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographic;
import com.agh.northwindproject.Customers.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CustomerCustomerDemoRepository extends MongoRepository<CustomerCustomerDemo, String> {
    List<CustomerCustomerDemo> findAll();
    List<CustomerCustomerDemo> findByCustomerDemographic(CustomerDemographic customerDemographic);
    CustomerCustomerDemo findByCustomer(Customer customer);
}
