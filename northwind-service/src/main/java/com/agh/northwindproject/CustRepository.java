package com.agh.northwindproject;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustRepository extends MongoRepository<Cust, String> {

    public Cust findByFirstName(String firstName);
    public List<Cust> findByLastName(String lastName);

}
