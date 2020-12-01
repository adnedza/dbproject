package com.agh.northwindproject.Territories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface TerritoriesRepository extends MongoRepository<Territory, BigInteger> {
}
