package com.agh.northwindproject.Region;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(value = "regions")
public class Region {
    @Id
    private String id;

    private String regionDescription;
}
