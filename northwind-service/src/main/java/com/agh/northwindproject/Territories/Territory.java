package com.agh.northwindproject.Territories;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "territories")
public class Territory {
    @Id
    private BigInteger territoryID;

    private String territoryDescription;

    private BigInteger regionID;
}
