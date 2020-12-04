package com.agh.northwindproject.Region;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface RegionsRepository extends MongoRepository<Region, String> {
    List<Region> findAll();
    Region findByRegionDescription(String regionDescription);
}
