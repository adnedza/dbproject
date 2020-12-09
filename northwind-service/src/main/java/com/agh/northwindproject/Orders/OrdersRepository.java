package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Customers.Customer;
import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Shippers.Shipper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrdersRepository extends MongoRepository<Order, String> {
    List<Order> findAll();
    List<Order> findByCustomerIDAndEmployeeID(String customerID, String employeeID);
}
