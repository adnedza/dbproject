package com.agh.northwindproject.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping(value = "/api/employees")
    @ResponseBody
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeesRepository.findAll());
    }

    @PostMapping(value = "/api/employee")
    @ResponseBody
    public ResponseEntity<String> addNewEmployee(@RequestBody Employee employee){
        employeesRepository.save(employee);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/employee/{lastName}/{firstName}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeByLastNameAndFirstName(@PathVariable String lastName,
                                                         @PathVariable String firstName){
        return ResponseEntity.ok(employeesRepository.findByLastNameAndFirstName(lastName, firstName));
    }

    @DeleteMapping(value = "/api/region/{lastName}/{firstName}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(@PathVariable String lastName,
                                               @PathVariable String firstName){
        Employee employee = employeesRepository.findByLastNameAndFirstName(lastName, firstName);
        if(employee != null){
            employeesRepository.delete(employee);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }
}
