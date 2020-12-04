package com.agh.northwindproject.Territories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TerritoriesRepository extends MongoRepository<Territory, String> {
    List<Territory> findAll();
    Territory findByTerritoryDescription(String categoryName);
}
