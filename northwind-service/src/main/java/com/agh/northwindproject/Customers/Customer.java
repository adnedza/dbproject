package com.agh.northwindproject.Customers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "customers")
public class Customer {
    @Id
    private String id;

    private String companyName;

    private String contactTitle;

    private String address;

    private String Region;

    private String postalCode;

    private String country;

    private String phone;

    private String fax;
}
