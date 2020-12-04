package com.agh.northwindproject.Shippers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "shippers")
@NoArgsConstructor
@Getter @Setter
public class Shipper {
    @Id
    private String id;

    private String companyName;

    private String phone;

    public Shipper(String companyName, String phone) {
        this.companyName = companyName;
        this.phone = phone;
    }
}
