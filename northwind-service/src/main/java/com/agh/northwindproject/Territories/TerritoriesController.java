package com.agh.northwindproject.Territories;

import com.agh.northwindproject.Region.RegionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @PostMapping(value = "/api/territory/")
    @ResponseBody
    public ResponseEntity<String> addNewTerritory(@RequestBody TerritoriesRequestBody territoriesRequestBody) {
        Territory territory = new Territory(territoriesRequestBody);
        territory.setRegion(regionsRepository.findById(territoriesRequestBody.getRegionID()).get());
        territoriesRepository.save(territory);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/territory/{territoryDescription}")
    @ResponseBody
    public ResponseEntity<Territory> getTerritoryByDescription(@PathVariable String territoryDescription){
        return ResponseEntity.ok(territoriesRepository.findByTerritoryDescription(territoryDescription));
    }

    @DeleteMapping(value = "/api/territory/{territoryID}")
    @ResponseBody
    public ResponseEntity<String> deleteTerritory(@PathVariable String territoryID){
        Territory territory = territoriesRepository.findById(territoryID).orElse(null);
        if(territory != null){
            territoriesRepository.delete(territory);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"territory not existing\"");
    }
}
