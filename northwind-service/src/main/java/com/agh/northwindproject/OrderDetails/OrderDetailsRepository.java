package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.Orders.Order;
import com.agh.northwindproject.Products.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {
    List<OrderDetails> findAll();
    OrderDetails findByOrderAndProduct(Order order, Product product);
}
