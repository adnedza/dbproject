package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.Orders.Order;
import com.agh.northwindproject.Products.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "orderDetails")
@NoArgsConstructor
@Getter @Setter
public class OrderDetails {
    @Id
    private String id;

    private Order order;

    private Product product;

    private int unitPrice;

    private int quantity;

    private double discount;

    public OrderDetails(Order order, Product product, int unitPrice,
                        int quantity, double discount) {
        this.order = order;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }
}
