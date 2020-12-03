package com.agh.northwindproject.CustomerDemographics;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "customerDemographics")
public class CustomerDemographic {
    @Id
    private String id;

    private String customerDesc;
}
