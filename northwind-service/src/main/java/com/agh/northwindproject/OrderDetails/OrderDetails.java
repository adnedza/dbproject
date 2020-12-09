package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.Orders.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderDetails")
@NoArgsConstructor
@Getter @Setter
public class OrderDetails {
    @Id
    private String id;

    private String productID;

    private int unitPrice;

    private int quantity;

    private double discount;

    public OrderDetails(Order order, String productID, int unitPrice,
                        int quantity, double discount) {
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderDetails(OrderDetailsRequestBody orderDetailsRequestBody ) {
        this.unitPrice = orderDetailsRequestBody.getUnitPrice();
        this.quantity = orderDetailsRequestBody.getQuantity();
        this.discount = orderDetailsRequestBody.getDiscount();
    }
}
