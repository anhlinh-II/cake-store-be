package com.vn.cake_store.dto;

import java.time.LocalDateTime;

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
     private LocalDateTime orderDate;

     public OrderDTO(Order order) {
          this.orderId = order.getOrderId();
          this.status = order.getStatus();
          this.orderDate = order.getOrderDate();
          // Initialize other fields as needed
     }
}
