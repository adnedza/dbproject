package com.agh.northwindproject.Employees;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

@Document(collection = "employees")
@NoArgsConstructor
@Getter @Setter
public class Employee {
    @Id
    private String id;

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

    public Employee(String lastName, String firstName,
                    String title, String titleOfCourtesy,
                    Date birthDate, Date hireDate, String address,
                    String city, String region, String postalCode,
                    String country, String homePhone, String extension,
                    String photo, String notes, String reportsTo, String photoPath) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.photo = photo;
        this.notes = notes;
        this.reportsTo = reportsTo;
        this.photoPath = photoPath;
    }
}
