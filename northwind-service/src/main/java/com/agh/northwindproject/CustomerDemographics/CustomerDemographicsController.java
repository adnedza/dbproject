package com.agh.northwindproject.CustomerDemographics;

import com.agh.northwindproject.CustomerCustomerDemo.CustomerCustomerDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerDemographicsController {
    @GetMapping("/api/customerDemopgraphics")
    public String getTest() {
        return "customerDemopgraphics controller";
    }
}
