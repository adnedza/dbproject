package com.agh.northwindproject.Employees;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface EmployeesRepository extends MongoRepository<Employee, BigInteger> {
}
