package com.agh.northwindproject.Shippers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippersController {
    @GetMapping("/api/shippers")
    public String getTest() {
        return "shippers controller";
    }
}
