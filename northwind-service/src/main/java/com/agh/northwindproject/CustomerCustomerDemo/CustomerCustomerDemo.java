package com.agh.northwindproject.CustomerCustomerDemo;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographic;
import com.agh.northwindproject.Customers.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "customerCustomerDemo")
@NoArgsConstructor
@Getter @Setter
public class CustomerCustomerDemo {
    @Id
    private String id;

    private Customer customer;

    private CustomerDemographic customerDemographic;

}
