package com.agh.northwindproject.Suppliers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuppliersController {
    @GetMapping("/api/suppliers")
    private String getTest() {
        return "Suppliers controller";
    }
}
