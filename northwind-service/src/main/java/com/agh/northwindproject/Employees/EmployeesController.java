package com.agh.northwindproject.Employees;

import com.agh.northwindproject.Customers.Customer;
import com.agh.northwindproject.EmployeeTerritories.EmployeeTerritoriesRepository;
import com.agh.northwindproject.EmployeeTerritories.EmployeeTerritory;
import com.agh.northwindproject.Region.RegionsRepository;
import com.agh.northwindproject.Territories.TerritoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EmployeesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private TerritoriesRepository territoriesRepository;

    @Autowired
    private RegionsRepository regionsRepository;

    @Autowired
    private EmployeeTerritoriesRepository employeeTerritoriesRepository;

    @GetMapping(value = "/api/employees")
    @ResponseBody
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeesRepository.findAll());
    }

    @PostMapping(value = "/api/employee")
    @ResponseBody
    public ResponseEntity<String> addNewEmployee(@RequestBody EmployeeRequestBody employeeRequestBody){
        Employee employee = new Employee(employeeRequestBody);

        for (String territoryDescription : employeeRequestBody.getEmployeeTerritories()) {
            EmployeeTerritory employeeTerritory = new EmployeeTerritory(
                    territoriesRepository.findByTerritoryDescription(territoryDescription));
            employee.getEmployeeTerritories().add(employeeTerritory);
            employeeTerritoriesRepository.save(employeeTerritory);
        }
        employeesRepository.save(employee);

        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/employee/{lastName}/{firstName}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeByLastNameAndFirstName(@PathVariable String lastName,
                                                         @PathVariable String firstName){
        return ResponseEntity.ok(employeesRepository.findByLastNameAndFirstName(lastName, firstName));
    }

    @GetMapping(value = "/api/employee/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Employee>> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(employeesRepository.findById(id));
    }


    @DeleteMapping(value = "/api/employee/{employeeID}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeID){
        Employee employee = employeesRepository.findById(employeeID).get();
        if(employee != null){
            employeesRepository.delete(employee);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"employee not existing\"");
    }
}
