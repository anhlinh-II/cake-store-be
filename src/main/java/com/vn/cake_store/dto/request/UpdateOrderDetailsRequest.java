package com.vn.cake_store.dto.request;

import lombok.Data;

@Data
public class UpdateOrderDetailsRequest {
     private Long orderDetailsId;

     private Long orderId;

     private Long productId;

     private Long quantity;
     private Double price;
}
