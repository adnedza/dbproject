package com.agh.northwindproject.Region;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface RegionsRepository extends MongoRepository<Region, BigInteger> {
}
