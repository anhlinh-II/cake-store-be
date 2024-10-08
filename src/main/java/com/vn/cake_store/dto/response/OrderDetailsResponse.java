package com.vn.cake_store.dto.response;

import com.vn.cake_store.entity.OrderDetails;
import com.vn.cake_store.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsResponse { 

     private Long orderDetailsId;

     private Long orderId;
     private Product product;

     private Long quantity;
     private Double price;

     public OrderDetailsResponse(OrderDetails orderDetails) {
          this.orderDetailsId = orderDetails.getOrderDetailsId();
          this.orderId = orderDetails.getOrder().getOrderId();
          this.product = orderDetails.getProduct();
          this.price = orderDetails.getPrice();
          this.quantity = orderDetails.getQuantity();
     }
}
