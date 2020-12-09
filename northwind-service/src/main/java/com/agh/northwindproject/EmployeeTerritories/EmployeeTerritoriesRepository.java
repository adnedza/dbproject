package com.agh.northwindproject.EmployeeTerritories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeTerritoriesRepository extends MongoRepository<EmployeeTerritory, String> {
    List<EmployeeTerritory> findAll();
}
