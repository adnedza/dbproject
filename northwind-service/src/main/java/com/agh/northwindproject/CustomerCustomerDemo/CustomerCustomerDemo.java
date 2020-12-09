package com.agh.northwindproject.CustomerCustomerDemo;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customerCustomerDemo")
@NoArgsConstructor
@Getter @Setter
public class CustomerCustomerDemo {
    @Id
    private String id;

    private CustomerDemographic customerDemographic;

    public CustomerCustomerDemo(CustomerDemographic customerDemographic) {
        this.customerDemographic = customerDemographic;
    }
}
