package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Customers.CustomersRepository;
import com.agh.northwindproject.Employees.EmployeesRepository;
import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Products.ProductsRespository;
import com.agh.northwindproject.Shippers.ShippersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ShippersRepository shippersRepository;

    @Autowired
    private ProductsRespository productsRespository;

    @GetMapping(value = "/api/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(ordersRepository.findAll());
    }

    @GetMapping(value = "/api/order/{orderId}")
    @ResponseBody
    public ResponseEntity<Order> getOrder(@PathVariable String orderId){
        Optional<Order> order = ordersRepository.findById(orderId);
        if(order.isPresent())
            return ResponseEntity.ok(order.get());
        return null;
    }

    @PostMapping(value = "/api/order")
    @ResponseBody
    public ResponseEntity<String> addNewOrder(@RequestBody OrderRequestBody orderRequestBody){
        Order order = new Order(orderRequestBody);

        order.setCustomerID(customersRepository.findByCompanyName(orderRequestBody.getCustomerCompanyName()).getId());
        order.setEmployeeID(employeesRepository.findByLastNameAndFirstName(orderRequestBody.getEmployeeLastName(),
                orderRequestBody.getEmployeeFirstName()).getId());
        order.setShipperID(shippersRepository.findByCompanyName(orderRequestBody.getShipperCompanyName()).getId());
        order.setOrderDate( Calendar.getInstance().getTime());

        for(OrderDetailsRequestBody orderDetailsRequestBody : orderRequestBody.getOrderDetails()) {
            if(productsRespository.findByProductName(orderDetailsRequestBody.getProductName()).getUnitsInStock()
                    - orderDetailsRequestBody.getQuantity() >= 0) {
                Product product = productsRespository.findByProductName(orderDetailsRequestBody.getProductName());
                OrderDetails orderDetails = new OrderDetails(product, orderDetailsRequestBody);
                order.getOrderDetailNews().add(orderDetails);
                product.setUnitsInOrder(product.getUnitsInOrder() + orderDetailsRequestBody.getQuantity());
                product.setUnitsInStock(product.getUnitsInStock() - orderDetailsRequestBody.getQuantity());
                productsRespository.save(product);
            }
        }

        ordersRepository.save(order);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/order/{customerCompanyName}/{employeeLastName}/{employeeFirstName}")
    @ResponseBody
    public ResponseEntity<List<Order>> getOrdersByCustomerAndEmployee(@PathVariable String customerCompanyName, @PathVariable String employeeLastName, @PathVariable String employeeFirstName){
        return ResponseEntity.ok(ordersRepository.findByCustomerIDAndEmployeeID(
                customersRepository.findByCompanyName(customerCompanyName).getId(),
                employeesRepository.findByLastNameAndFirstName(employeeLastName, employeeFirstName).getId()
        ));
    }

    @DeleteMapping(value = "/api/order/{orderID}")
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@PathVariable String orderID){
        Order order = ordersRepository.findById(orderID).get();
        if(order != null){
            ordersRepository.delete(order);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"order not exists\"");
    }
}
