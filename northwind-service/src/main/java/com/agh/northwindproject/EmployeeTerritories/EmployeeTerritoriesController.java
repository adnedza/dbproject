package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Employees.EmployeesRepository;
import com.agh.northwindproject.Territories.TerritoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeTerritoriesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private TerritoriesRepository territoriesRepository;

    @Autowired
    private EmployeeTerritoriesRepository employeeTerritoriesRepository;

    @GetMapping(value = "/api/employeeTerritories")
    @ResponseBody
    public ResponseEntity<List<EmployeeTerritory>> getAllEmployeeTerritories(){
        return ResponseEntity.ok(employeeTerritoriesRepository.findAll());
    }

    @PostMapping(value = "/api/employeeTerritory")
    @ResponseBody
    public ResponseEntity<String> addNewEmployeeTerritory(@RequestBody EmployeeTerritoryRequestBody employeeTerritoryRequestBody){
        EmployeeTerritory employeeTerritory = new EmployeeTerritory();

        employeeTerritory.setEmployee(employeesRepository.findByLastNameAndFirstName(
                employeeTerritoryRequestBody.getLastName(),
                employeeTerritoryRequestBody.getFirstName()));

        employeeTerritory.setTerritory(territoriesRepository.findByTerritoryDescription(
                employeeTerritoryRequestBody.getTerritoryDescription()));

        employeeTerritoriesRepository.save(employeeTerritory);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/employeesByTerritory/{territoryDescription}")
    @ResponseBody
    public ResponseEntity<List<EmployeeTerritory>> getEmployeesByTerritory(@PathVariable String territoryDescription){
        return ResponseEntity.ok(employeeTerritoriesRepository.findByTerritory(
                territoriesRepository.findByTerritoryDescription(territoryDescription)));
    }

    @GetMapping(value = "/api/employeeTerritory/{lastName}/{firstName}")
    @ResponseBody
    public ResponseEntity<EmployeeTerritory> getEmployeeTerritoryByEmployeeName(@PathVariable String lastName,
                                                                     @PathVariable String firstName){
        return ResponseEntity.ok(employeeTerritoriesRepository.findByEmployee(
                employeesRepository.findByLastNameAndFirstName(lastName, firstName)));
    }

    @DeleteMapping(value = "/api/employeeTerritory/{lastName}/{firstName}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployeeTerritory(@PathVariable String lastName,
                                                @PathVariable String firstName){
        EmployeeTerritory employeeTerritory = employeeTerritoriesRepository.
                findByEmployee(employeesRepository.findByLastNameAndFirstName(lastName, firstName));
        if(employeeTerritory != null){
            employeeTerritoriesRepository.delete(employeeTerritory);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"product already not existing, cannot remove\"");
    }
}
