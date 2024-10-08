package com.vn.cake_store.mapper;

import com.vn.cake_store.dto.response.OrderDetailsResponse;
import com.vn.cake_store.entity.OrderDetails;

public class OrderDetailsMapper {
     public static OrderDetailsResponse toOrderDetailsResponse(OrderDetails orderDetails) {
          OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
          orderDetailsResponse.setOrderDetailsId(orderDetails.getOrderDetailsId());
          orderDetailsResponse.setOrderId(orderDetails.getOrder().getOrderId());
          orderDetailsResponse.setPrice(orderDetails.getPrice());
          orderDetailsResponse.setProductId(orderDetails.getProduct().getProductId());
          orderDetailsResponse.setQuantity(orderDetails.getQuantity());
          
         return orderDetailsResponse;
     }
}
