package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.Orders.Order;
import com.agh.northwindproject.Products.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "orderDetails")
public class OrderDetails {
    @Id
    private BigInteger id;

    private Order order;

    private Product product;

    private int unitPrice;

    private int quantity;

    private double discount;
}
