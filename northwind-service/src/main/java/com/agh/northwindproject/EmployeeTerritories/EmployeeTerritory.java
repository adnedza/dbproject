package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Territories.Territory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "employeeTerritories")
@NoArgsConstructor
@Getter @Setter
public class EmployeeTerritory {
    @Id
    private String id;

    private Employee employee;

    private Territory territory;

    public EmployeeTerritory(Employee employee, Territory territory) {
        this.employee = employee;
        this.territory = territory;
    }
}
