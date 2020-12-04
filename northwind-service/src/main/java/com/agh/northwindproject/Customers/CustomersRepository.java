package com.agh.northwindproject.Customers;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomersRepository extends MongoRepository<Customer, String> {
    List<Customer> findAll();
    Customer findByCompanyName(String companyName);
}
