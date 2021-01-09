package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Products.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class OrderDetails {
    private String productID;
    private String productName;

    private int unitPrice;
    private int quantity;
    private double discount;

    public OrderDetails(Product product, OrderDetailsRequestBody orderDetailsRequestBody) {
        this.productID = product.getId();
        this.productName = product.getProductName();
        this.unitPrice = orderDetailsRequestBody.getUnitPrice();
        this.quantity = orderDetailsRequestBody.getQuantity();
        this.discount = orderDetailsRequestBody.getDiscount();
    }
}
