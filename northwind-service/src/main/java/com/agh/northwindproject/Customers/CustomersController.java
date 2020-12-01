package com.agh.northwindproject.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersController {
    @GetMapping("/api/customer")
    public String getTest() {
        return "customer controller";
    }
}
