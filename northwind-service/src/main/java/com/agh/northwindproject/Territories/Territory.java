package com.agh.northwindproject.Territories;

import com.agh.northwindproject.Region.Region;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "territories")
public class Territory {
    @Id
    private String id;

    private String territoryDescription;

    private Region region;
}
