package com.vn.cake_store.dto.response;

import com.vn.cake_store.entity.OrderDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsResponse { 

     private Long orderDetailsId;

     private Long orderId;
     private Long productId;

     private Long quantity;
     private Double price;

     public OrderDetailsResponse(OrderDetails orderDetails) {
          this.orderDetailsId = orderDetails.getOrderDetailsId();
          this.orderId = orderDetails.getOrder().getOrderId();
          this.productId = orderDetails.getProduct().getProductId();
          this.price = orderDetails.getPrice();
          this.quantity = orderDetails.getQuantity();
     }
}
