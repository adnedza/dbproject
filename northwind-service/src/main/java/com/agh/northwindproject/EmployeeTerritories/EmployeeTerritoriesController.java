package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeTerritoriesController {
    @GetMapping("/api/employeesTerritories")
    public String getTest() {
        return "employeesTerritories controller";
    }
}
