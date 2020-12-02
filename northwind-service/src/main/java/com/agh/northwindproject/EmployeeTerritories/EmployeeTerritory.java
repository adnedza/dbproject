package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Territories.Territory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "employeeTerritories")
public class EmployeeTerritory {
    @Id
    private BigInteger id;

    private Employee employee;

    private Territory territory;
}
