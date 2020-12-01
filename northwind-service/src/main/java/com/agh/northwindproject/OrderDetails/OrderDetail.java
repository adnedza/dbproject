package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.EmployeeTerritories.EmployeeTerritory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Document(collection = "orderDetails")
public class OrderDetail {
    @Id
    private CompositeKey id;


    static class CompositeKey implements Serializable {
        private BigInteger orderID;
        private BigInteger productID;
    }

    private int unitPrice;

    private int quantity;

    private double discount;
}
