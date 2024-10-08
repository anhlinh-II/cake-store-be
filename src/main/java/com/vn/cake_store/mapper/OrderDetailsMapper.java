package com.vn.cake_store.mapper;

import com.vn.cake_store.dto.response.OrderDetailsResponse;
import com.vn.cake_store.entity.OrderDetails;

public class OrderDetailsMapper {
     public static OrderDetailsResponse toOrderDetailsResponse(OrderDetails orderDetails) {
          OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
          orderDetailsResponse.setOrderDetailsId(orderDetails.getOrderDetailsId());
          orderDetailsResponse.setOrderId(orderDetails.getOrder().getOrderId());
          orderDetailsResponse.setPrice(orderDetails.getPrice());
          orderDetailsResponse.setProduct(orderDetails.getProduct());
          orderDetailsResponse.setQuantity(orderDetails.getQuantity());
          // orderDetailsResponse.setCustomerId(order.getCustomer().getCustomerId());
          // orderDetailsResponse.setOrderDate(order.getOrderDate());
          // orderDetailsResponse.setStatus(order.getStatus());
         return orderDetailsResponse;
     }
}
