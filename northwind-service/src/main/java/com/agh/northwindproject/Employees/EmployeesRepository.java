package com.agh.northwindproject.Employees;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeesRepository extends MongoRepository<Employee, String> {
    List<Employee> findAll();
    Employee findByLastNameAndFirstName(String lastName, String firstName);

}
