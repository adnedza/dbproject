package com.agh.northwindproject.OrderDetails;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {
}
