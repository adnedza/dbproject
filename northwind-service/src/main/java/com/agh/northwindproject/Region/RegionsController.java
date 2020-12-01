package com.agh.northwindproject.Region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionsController {
    @GetMapping("/api/regions")
    public String getTest() {
        return "regions controller";
    }
}
