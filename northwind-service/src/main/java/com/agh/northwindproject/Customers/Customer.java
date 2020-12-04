package com.agh.northwindproject.Customers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "customers")
@NoArgsConstructor
@Getter @Setter
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

    public Customer(String companyName, String contactTitle, String address, String region,
                    String postalCode, String country, String phone, String fax) {
        this.companyName = companyName;
        this.contactTitle = contactTitle;
        this.address = address;
        Region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
    }
}
