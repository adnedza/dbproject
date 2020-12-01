package com.agh.northwindproject.Employees;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

@Document(collection = "employees")
public class Employee {
    @Id
    private BigInteger employeeID;

    private String lastName;

    private String firstName;

    private String title;

    private String titleOfCourtesy;

    private Date birthDate;

    private Date hireDate;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String homePhone;

    private String extension;

    private String photo;

    private String notes;

    private String reportsTo;

    private String photoPath;
}
