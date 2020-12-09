package com.agh.northwindproject.EmployeeTerritories;

import com.agh.northwindproject.Territories.Territory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employeeTerritories")
@NoArgsConstructor
@Getter @Setter
public class EmployeeTerritory {
    @Id
    private String id;

    private Territory territory;

    public EmployeeTerritory(Territory territory) {
        this.territory = territory;
    }
}
