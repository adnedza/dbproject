package com.agh.northwindproject.EmployeeTerritories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface EmployeeTerritoriesRepository extends MongoRepository<EmployeeTerritory, BigInteger> {
}
