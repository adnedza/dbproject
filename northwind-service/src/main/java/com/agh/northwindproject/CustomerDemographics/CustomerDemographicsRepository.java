package com.agh.northwindproject.CustomerDemographics;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CustomerDemographicsRepository extends MongoRepository<CustomerDemographic, String> {
    List<CustomerDemographic> findAll();
    CustomerDemographic findByCustomerDesc(String customerDesc);
}
