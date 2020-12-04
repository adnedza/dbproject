package com.agh.northwindproject.Shippers;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippersRepository extends MongoRepository<Shipper, String> {
}
