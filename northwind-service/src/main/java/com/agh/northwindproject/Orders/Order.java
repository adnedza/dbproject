package com.agh.northwindproject.Orders;

import com.agh.northwindproject.OrderDetails.OrderDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@NoArgsConstructor
@Getter @Setter
public class Order {
    @Id
    private String id;

    private String customerID;

    private String employeeID;

    private String shipperID;

    private Date orderDate;

    private Date requiredDate;

    private Date shippedDate;

    private String freight;

    private String shipName;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

    @DBRef
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Order(String customerID, String employeeID, String shipperID, Date orderDate,
                 Date requiredDate, Date shippedDate,
                 String freight, String shipName, String shipCity, String shipRegion,
                 String shipPostalCode, String shipCountry) {
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.shipperID = shipperID;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.freight = freight;
        this.shipName = shipName;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }

    public Order(OrderRequestBody orderRequestBody) {
        this.orderDate = orderRequestBody.getOrderDate();
        this.requiredDate = orderRequestBody.getRequiredDate();
        this.shippedDate = orderRequestBody.getShippedDate();
        this.freight = orderRequestBody.getFreight();
        this.shipName = orderRequestBody.getShipName();
        this.shipCity = orderRequestBody.getShipCity();
        this.shipRegion = orderRequestBody.getShipRegion();
        this.shipPostalCode = orderRequestBody.getShipPostalCode();
        this.shipCountry = orderRequestBody.getShipCountry();
    }
}
