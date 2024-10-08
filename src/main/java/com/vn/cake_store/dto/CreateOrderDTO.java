package com.vn.cake_store.dto;

import com.vn.cake_store.entity.constants.OrderStatus;

import lombok.Data;

@Data
public class CreateOrderDTO {
     private Long customerId;
     private OrderStatus status;
}
