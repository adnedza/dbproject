package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Customers.Customer;
import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Shippers.Shipper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

@Document(collection = "orders")
public class Order {
    @Id
    private BigInteger id;

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
}
