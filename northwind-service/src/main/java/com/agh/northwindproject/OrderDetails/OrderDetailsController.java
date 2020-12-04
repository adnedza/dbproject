package com.agh.northwindproject.OrderDetails;

import com.agh.northwindproject.Customers.CustomersRepository;
import com.agh.northwindproject.Employees.EmployeesRepository;
import com.agh.northwindproject.Orders.Order;
import com.agh.northwindproject.Orders.OrdersRepository;
import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Products.ProductsRespository;
import com.agh.northwindproject.Shippers.ShippersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ProductsRespository productsRespository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ShippersRepository shippersRepository;

    @Autowired
    private EmployeesRepository employeesRepository;


    @GetMapping(value = "/api/orderdetails")
    @ResponseBody
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails(){
        return ResponseEntity.ok(orderDetailsRepository.findAll());
    }

    @PostMapping(value = "/api/orderdetail")
    @ResponseBody
    public ResponseEntity<String> addNewOrderDetails(@RequestBody OrderDetails orderDetails){
        orderDetails.setProduct(productsRespository.findByProductName(orderDetails.getProduct().getProductName()));
        orderDetails.setOrder(ordersRepository.findByCustomerAndEmployeeAndShipVia(customersRepository.findByCompanyName(orderDetails.getOrder().getCustomer().getCompanyName()),
                employeesRepository.findByLastNameAndFirstName(orderDetails.getOrder().getEmployee().getLastName(), orderDetails.getOrder().getEmployee().getFirstName()),
                shippersRepository.findByCompanyName(orderDetails.getOrder().getShipVia().getCompanyName())));
        orderDetailsRepository.save(orderDetails);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/orderdetails/productName={productName}&shipperCompanyName={shipperCompanyName}&employeeLastName={employeeLastName}&employeeFirstName={employeeFirstName}&customerCompanyName={customerCompanyName}")
    @ResponseBody
    public ResponseEntity<OrderDetails> getOrderDetailsByOrderAndProduct(@PathVariable String shipperCompanyName, @PathVariable String productName, @PathVariable String employeeLastName, @PathVariable String employeeFirstName, @PathVariable String customerCompanyName){
        Order order = ordersRepository.findByCustomerAndEmployeeAndShipVia(customersRepository.findByCompanyName(customerCompanyName),
                employeesRepository.findByLastNameAndFirstName(employeeLastName, employeeFirstName), shippersRepository.findByCompanyName(shipperCompanyName));
        Product product = productsRespository.findByProductName(productName);
        return ResponseEntity.ok(orderDetailsRepository.findByOrderAndProduct(order, product));
    }

    @DeleteMapping(value = "/api/orderdetails/productName={productName}&shipperCompanyName={shipperCompanyName}&employeeLastName={employeeLastName}&employeeFirstName={employeeFirstName}&customerCompanyName={customerCompanyName}")
    @ResponseBody
    public ResponseEntity<String> deleteOrderDetails(@PathVariable String shipperCompanyName, @PathVariable String productName, @PathVariable String employeeLastName, @PathVariable String employeeFirstName, @PathVariable String customerCompanyName){
        Order order = ordersRepository.findByCustomerAndEmployeeAndShipVia(customersRepository.findByCompanyName(customerCompanyName),
                employeesRepository.findByLastNameAndFirstName(employeeLastName, employeeFirstName), shippersRepository.findByCompanyName(shipperCompanyName));
        Product product = productsRespository.findByProductName(productName);
        OrderDetails orderDetails = orderDetailsRepository.findByOrderAndProduct(order, product);
        if(orderDetails != null){
            orderDetailsRepository.delete(orderDetails);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"order detail not existing\"");
    }
}
