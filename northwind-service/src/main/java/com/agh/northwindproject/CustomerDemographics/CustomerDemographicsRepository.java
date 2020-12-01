package com.agh.northwindproject.CustomerDemographics;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface CustomerDemographicsRepository extends MongoRepository<CustomerDemographic, BigInteger> {
}
