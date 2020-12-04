package com.agh.northwindproject.Shippers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShippersController {
    @Autowired
    private ShippersRepository shippersRepository;

    @GetMapping(value = "/api/shippers")
    @ResponseBody
    public ResponseEntity<List<Shipper>> getAllShippers(){
        return ResponseEntity.ok(shippersRepository.findAll());
    }

    @PostMapping(value = "/api/shipper")
    @ResponseBody
    public ResponseEntity<String> addNewShipper(@RequestBody Shipper shipper){
        shippersRepository.save(shipper);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/shipper/{companyName}")
    @ResponseBody
    public ResponseEntity<Shipper> getShipperByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(shippersRepository.findByCompanyName(companyName));
    }

    @DeleteMapping(value = "/api/shipper/{companyName}")
    @ResponseBody
    public ResponseEntity<String> deleteShipper(@PathVariable String companyName){
        Shipper shipper = shippersRepository.findByCompanyName(companyName);
        if(shipper != null){
            shippersRepository.delete(shipper);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"shipper not existing\"");
    }
}
