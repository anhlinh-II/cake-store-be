package com.vn.cake_store.dto;

import java.time.LocalDateTime;

import com.vn.cake_store.entity.constants.OrderStatus;

import lombok.Data;

@Data
public class CreateOrderDTO {
     private Long customerId;
     private LocalDateTime orderDate;
     private OrderStatus status;
}
