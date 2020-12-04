package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Territories.Territory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeTerritoriesRepository extends MongoRepository<EmployeeTerritory, String> {
    List<EmployeeTerritory> findAll();
    List<EmployeeTerritory> findByTerritory(Territory territory);
    EmployeeTerritory findByEmployee(Employee employee);
}
