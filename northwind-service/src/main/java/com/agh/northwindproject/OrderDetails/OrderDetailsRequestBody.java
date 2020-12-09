package com.agh.northwindproject.OrderDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class OrderDetailsRequestBody {
    private String productName;

    private int unitPrice;

    private int quantity;

    private double discount;
}
