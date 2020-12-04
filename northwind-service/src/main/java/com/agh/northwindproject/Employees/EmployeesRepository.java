package com.agh.northwindproject.Employees;

import com.agh.northwindproject.Region.Region;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface EmployeesRepository extends MongoRepository<Employee, String> {
    List<Employee> findAll();
    Employee findByLastNameAndFirstName(String lastName, String firstName);

}
