package com.agh.northwindproject.CustomerCustomerDemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CustomerCustomerDemoRepository extends MongoRepository<CustomerCustomerDemo, String> {
    List<CustomerCustomerDemo> findAll();
}
