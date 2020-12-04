package com.agh.northwindproject.OrderDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailsController {
    @GetMapping("/api/ordersDetails")
    public String getTest() {
        return "ordersDetails controller";
    }
}
