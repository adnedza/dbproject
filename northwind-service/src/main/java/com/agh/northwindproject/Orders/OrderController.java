package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Categories.CategoriesRepository;
import com.agh.northwindproject.Customers.CustomersRepository;
import com.agh.northwindproject.Employees.EmployeesRepository;
import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Products.ProductsRespository;
import com.agh.northwindproject.Shippers.ShippersRepository;
import com.agh.northwindproject.Suppliers.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ShippersRepository shippersRepository;

    @GetMapping(value = "/api/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(ordersRepository.findAll());
    }

    @PostMapping(value = "/api/order")
    @ResponseBody
    public ResponseEntity<String> addNewOrder(@RequestBody Order order){
        order.setCustomer(customersRepository.findByCompanyName(order.getCustomer().getCompanyName()));
        order.setEmployee(employeesRepository.findByLastNameAndFirstName(order.getEmployee().getLastName(), order.getEmployee().getFirstName()));
        order.setShipVia(shippersRepository.findByCompanyName(order.getShipVia().getCompanyName()));
        ordersRepository.save(order);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/order/customerCompanyName={customerCompanyName}&shipperCompanyName={shipperCompanyName}&employeeLastName={employeeLastName}&employeeFirstName={employeeFirstName}")
    @ResponseBody
    public ResponseEntity<Order> getOrderByCustomerAndEmployeeAndShipVia(@PathVariable String customerCompanyName, @PathVariable String shipperCompanyName, @PathVariable String employeeLastName, @PathVariable String employeeFirstName){
        return ResponseEntity.ok(ordersRepository.findByCustomerAndEmployeeAndShipVia(customersRepository.findByCompanyName(customerCompanyName),
                employeesRepository.findByLastNameAndFirstName(employeeLastName, employeeFirstName), shippersRepository.findByCompanyName(shipperCompanyName)));
    }

    @DeleteMapping(value = "/api/order/customerCompanyName={customerCompanyName}&shipperCompanyName={shipperCompanyName}&employeeLastName={employeeLastName}&employeeFirstName={employeeFirstName}")
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@PathVariable String customerCompanyName, @PathVariable String shipperCompanyName, @PathVariable String employeeLastName, @PathVariable String employeeFirstName){
        Order order = ordersRepository.findByCustomerAndEmployeeAndShipVia(customersRepository.findByCompanyName(customerCompanyName),
                employeesRepository.findByLastNameAndFirstName(employeeLastName, employeeFirstName), shippersRepository.findByCompanyName(shipperCompanyName));
        if(order != null){
            ordersRepository.delete(order);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"order not exists\"");
    }
}
