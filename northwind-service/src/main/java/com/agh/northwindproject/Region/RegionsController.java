package com.agh.northwindproject.Region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionsController {
    @Autowired
    private RegionsRepository regionsRepository;

    @GetMapping(value = "/api/regions")
    @ResponseBody
    public ResponseEntity<List<Region>> getAllRegions(){
        return ResponseEntity.ok(regionsRepository.findAll());
    }

    @PostMapping(value = "/api/region")
    @ResponseBody
    public ResponseEntity<String> addNewRegion(@RequestBody Region region){
        regionsRepository.save(region);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/region/{regionDescription}")
    @ResponseBody
    public ResponseEntity<Region> getRegionByDescription(@PathVariable String regionDescription){
        return ResponseEntity.ok(regionsRepository.findByRegionDescription(regionDescription));
    }

    @DeleteMapping(value = "/api/region/{regionDescription}")
    @ResponseBody
    public ResponseEntity<String> deleteRegion(@PathVariable String regionDescription){
        Region region = regionsRepository.findByRegionDescription(regionDescription);
        if(region != null){
            regionsRepository.delete(region);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }
}
