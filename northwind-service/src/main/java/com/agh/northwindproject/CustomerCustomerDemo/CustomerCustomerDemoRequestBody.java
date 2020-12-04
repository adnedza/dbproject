package com.agh.northwindproject.CustomerCustomerDemo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CustomerCustomerDemoRequestBody {
    private String companyName;
    private String customerDesc;

    public CustomerCustomerDemoRequestBody(String companyName, String customerDesc) {
        this.companyName = companyName;
        this.customerDesc = customerDesc;
    }
}
