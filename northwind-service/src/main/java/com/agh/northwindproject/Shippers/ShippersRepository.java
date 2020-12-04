package com.agh.northwindproject.Shippers;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShippersRepository extends MongoRepository<Shipper, String> {
    List<Shipper> findAll();
    Shipper findByCompanyName(String companyName);
}
