package com.agh.northwindproject.CustomerCustomerDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerCustomerDemoController {
    @GetMapping("/api/customerCustomerDemoController")
    public String getTest() {
        return "customerCustomerDemo controller";
    }
}
