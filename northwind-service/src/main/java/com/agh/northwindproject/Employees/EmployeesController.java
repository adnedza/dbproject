package com.agh.northwindproject.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {
    @GetMapping("/api/employees")
    public String getTest() {
        return "employees controller";
    }
}
