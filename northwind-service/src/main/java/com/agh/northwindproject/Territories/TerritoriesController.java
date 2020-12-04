package com.agh.northwindproject.Territories;

import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Region.RegionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TerritoriesController {
    @Autowired
    private TerritoriesRepository territoriesRepository;

    @Autowired
    private RegionsRepository regionsRepository;

    @GetMapping(value = "/api/territories")
    @ResponseBody
    public ResponseEntity<List<Territory>> getAllTerritories(){
        return ResponseEntity.ok(territoriesRepository.findAll());
    }

    @PostMapping(value = "/api/territory")
    @ResponseBody
    public ResponseEntity<String> addNewTerritory(@RequestBody Territory territory){
        territory.setRegion(regionsRepository.findByRegionDescription(territory.getRegion().getRegionDescription()));
        territoriesRepository.save(territory);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/territory/{territoryDescription}")
    @ResponseBody
    public ResponseEntity<Territory> getTerritoryByDescription(@PathVariable String territoryDescription){
        return ResponseEntity.ok(territoriesRepository.findByTerritoryDescription(territoryDescription));
    }

    @DeleteMapping(value = "/api/territory/{territoryDescription}")
    @ResponseBody
    public ResponseEntity<String> deleteTerritory(@PathVariable String territoryDescription){
        Territory territory = territoriesRepository.findByTerritoryDescription(territoryDescription);
        if(territory != null){
            territoriesRepository.delete(territory);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"product already not existing, cannot remove\"");
    }
}
