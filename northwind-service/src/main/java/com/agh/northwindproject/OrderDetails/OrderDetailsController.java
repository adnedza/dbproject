package com.agh.northwindproject.OrderDetails;


import com.agh.northwindproject.Orders.Order;
import com.agh.northwindproject.Orders.OrdersRepository;
import com.agh.northwindproject.Products.ProductsRespository;
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


    @GetMapping(value = "/api/orderDetails")
    @ResponseBody
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails(){
        return ResponseEntity.ok(orderDetailsRepository.findAll());
    }

    @PostMapping(value = "/api/orderDetail/{orderID}")
    @ResponseBody
    public ResponseEntity<String> addNewOrderDetailsToOrder(@RequestBody OrderDetailsRequestBody orderDetailsRequestBody,
                                                            @PathVariable String orderID){
        OrderDetails orderDetails = new OrderDetails(orderDetailsRequestBody);
        orderDetails.setProductID(productsRespository.findByProductName(orderDetailsRequestBody.getProductName()).getId());
        orderDetailsRepository.save(orderDetails);
        Order order = ordersRepository.findById(orderID).get();
        order.getOrderDetails().add(orderDetails);
        ordersRepository.save(order);

        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/orderDetails/{orderID}")
    @ResponseBody
    public ResponseEntity<List<OrderDetails>> getOrderDetailsByOrderID (@PathVariable String orderID){
        return ResponseEntity.ok(ordersRepository.findById(orderID).get().getOrderDetails());
    }

    @DeleteMapping(value = "/api/orderDetails/{orderID}/{orderDetailsID}")
    @ResponseBody
    public ResponseEntity<String> deleteOrderDetails(@PathVariable String orderID, @PathVariable String orderDetailsID){
        Order order = ordersRepository.findById(orderID).get();
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsID).get();
        if(orderDetails != null && order != null){
            orderDetailsRepository.delete(orderDetails);
            ordersRepository.save(order);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"order detail not existing\"");
    }
}
