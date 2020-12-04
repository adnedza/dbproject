package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Customers.Customer;
import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Shippers.Shipper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

@Document(collection = "orders")
@NoArgsConstructor
@Getter @Setter
public class Order {
    @Id
    private String id;

    private Customer customer;

    private Employee employee;

    private Date orderDate;

    private Date requiredDate;

    private Date shippedDate;

    private Shipper shipVia;

    private String freight;

    private String shipName;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

    public Order(Customer customer, Employee employee, Date orderDate,
                 Date requiredDate, Date shippedDate, Shipper shipVia,
                 String freight, String shipName, String shipCity, String shipRegion,
                 String shipPostalCode, String shipCountry) {
        this.customer = customer;
        this.employee = employee;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.shipName = shipName;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }
}
