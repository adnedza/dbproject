package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.Orders.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailsController {
    @GetMapping("/api/ordersDetails")
    public String getTest() {
        return "ordersDetails controller";
    }
}
