package com.agh.northwindproject.EmployeeTerritories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class EmployeeTerritoryRequestBody {
    private String firstName;
    private String lastName;
    private String territoryDescription;

    public EmployeeTerritoryRequestBody(String firstName, String lastName, String territoryDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.territoryDescription = territoryDescription;
    }
}
