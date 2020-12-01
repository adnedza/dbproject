package com.agh.northwindproject.EmployeeTerritories;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Document(collection = "employeeTerritories")
public class EmployeeTerritory {
    @Id
    private CompositeKey id;


    static class CompositeKey implements Serializable {
        private BigInteger employeeID;
        private BigInteger territoryID;
    }
}
