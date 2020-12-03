package com.agh.northwindproject.Shippers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "shippers")
public class Shipper {
    @Id
    private String id;

    private String companyName;

    private String phone;
}
