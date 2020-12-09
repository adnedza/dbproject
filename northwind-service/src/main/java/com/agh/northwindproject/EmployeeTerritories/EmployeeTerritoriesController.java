package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Employees.EmployeesRepository;
import com.agh.northwindproject.Territories.TerritoriesRepository;
import com.agh.northwindproject.Territories.Territory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeTerritoriesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private EmployeeTerritoriesRepository employeeTerritoriesRepository;

    @Autowired
    private TerritoriesRepository territoriesRepository;


    @GetMapping(value = "/api/employeeTerritories")
    @ResponseBody
    public ResponseEntity<List<EmployeeTerritory>> getAllEmployeeTerritories(){
        return ResponseEntity.ok(employeeTerritoriesRepository.findAll());
    }

    @PostMapping(value = "/api/employeeTerritories/{employeeID}/{territoryDescription}")
    @ResponseBody
    public ResponseEntity<String> addNewEmployeeTerritory(@PathVariable String employeeID,
                                                          @PathVariable String territoryDescription) {
        Employee employee = employeesRepository.findById(employeeID).get();
        if (employee != null) {
            Territory territory = territoriesRepository.findByTerritoryDescription(territoryDescription);
            if (territory != null) {
                EmployeeTerritory employeeTerritory = new EmployeeTerritory(territory);
                employee.getEmployeeTerritories().add(employeeTerritory);
                employeeTerritoriesRepository.save(employeeTerritory);
                employeesRepository.save(employee);
                return ResponseEntity.ok("\"status\": \"added\"");
            }
            return ResponseEntity.ok("\"status\": \"territory not existing\"");
        }
        return ResponseEntity.ok("\"status\": \"employee not existing\"");
    }

    @GetMapping(value = "/api/employeeTerritories/{employeeID}")
    @ResponseBody
    public ResponseEntity<List<EmployeeTerritory>> getEmployeeTerritoriesByEmployeeID(@PathVariable String employeeID) {
        return ResponseEntity.ok(employeesRepository.findById(employeeID).get().getEmployeeTerritories());
    }

    @DeleteMapping(value = "/api/employee/{employeeID}/{employeeTerritoryID}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployeeTerritory(@PathVariable String employeeID,
                                                            @PathVariable String employeeTerritoryID) {
        Employee employee = employeesRepository.findById(employeeID).get();
        if (employee != null) {
            EmployeeTerritory employeeTerritory = employeeTerritoriesRepository.findById(employeeTerritoryID).get();
            if (employeeTerritory != null) {
                employeeTerritoriesRepository.delete(employeeTerritory);
                return ResponseEntity.ok("\"status\": \"removed\"");
            }
            return ResponseEntity.ok("\"status\": \"territory not existing\"");
        }
        return ResponseEntity.ok("\"status\": \"employee not existing\"");
    }

}
