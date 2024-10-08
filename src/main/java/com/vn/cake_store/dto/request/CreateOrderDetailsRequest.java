package com.vn.cake_store.dto.request;

import java.util.Set;

import lombok.Data;

@Data
public class CreateOrderDetailsRequest {
     private Long orderId;
     private Long productId;

     private Long quantity;
     private Double price;
}
