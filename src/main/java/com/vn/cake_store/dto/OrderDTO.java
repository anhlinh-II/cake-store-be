package com.vn.cake_store.dto;

import java.time.Instant;

import com.vn.cake_store.entity.Order;
import com.vn.cake_store.entity.constants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDTO {
     private Long orderId;
     private OrderStatus status;
     private Instant orderDate;
     private Long customerId;

     public OrderDTO(Order order) {
          this.orderId = order.getOrderId();
          this.status = order.getStatus();
          this.orderDate = order.getOrderDate();
          this.customerId = order.getCustomer().getCustomerId();
          // Initialize other fields as needed
     }
}
