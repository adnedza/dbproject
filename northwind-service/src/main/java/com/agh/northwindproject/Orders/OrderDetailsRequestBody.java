package com.agh.northwindproject.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDetailsRequestBody {
    private String productName;
    private int unitPrice;
    private int quantity;
    private double discount;

    public OrderDetailsRequestBody(String productName, int unitPrice, int quantity, double discount) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }
}
