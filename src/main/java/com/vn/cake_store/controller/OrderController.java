package com.vn.cake_store.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.CreateOrderDTO;
import com.vn.cake_store.dto.OrderDTO;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.entity.Order;
import com.vn.cake_store.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
     private final OrderService orderService;

     @PostMapping
     public ApiResponse<Order> createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
          return this.orderService.createOrder(createOrderDTO);
     }

     @GetMapping
     public ApiResponse<List<OrderDTO>> getAllOrders() {
          List<Order> allOrders = this.orderService.getAllOrders();
          
          List<OrderDTO> orderDTOs = allOrders.stream()
                    .map(OrderDTO::new)
                    .collect(Collectors.toList());

          return ApiResponse.<List<OrderDTO>>builder()
                    .code(1000)
                    .message("Get all orders successfully!")
                    .result(orderDTOs)
                    .build();
     }

     @GetMapping("/customer/{customerId}")
     public ApiResponse<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
          // Fetch orders by customerId
          List<Order> orders = orderService.getOrderByCustomer(customerId);

          // Convert List<Order> to List<OrderDTO>
          List<OrderDTO> orderDTOs = orders.stream()
                    .map(OrderDTO::new) // Create OrderDTO from Order
                    .collect(Collectors.toList());

          return ApiResponse.<List<OrderDTO>>builder()
                    .code(1000)
                    .message("Get all orders of the user with id " + customerId + " successfully!")
                    .result(orderDTOs)
                    .build();
     }
}
