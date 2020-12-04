package com.agh.northwindproject.Orders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping("/api/orders")
    public String getTest() {
        return "orders controller";
    }
}
