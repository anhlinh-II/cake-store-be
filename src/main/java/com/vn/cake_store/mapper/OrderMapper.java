package com.vn.cake_store.mapper;

import com.vn.cake_store.dto.OrderDTO;
import com.vn.cake_store.entity.Order;

public class OrderMapper {

     public static OrderDTO toOrderDTO(Order order) {
         OrderDTO orderDTO = new OrderDTO();
         orderDTO.setOrderId(order.getOrderId());
         orderDTO.setCustomerId(order.getCustomer().getCustomerId());
         orderDTO.setOrderDate(order.getOrderDate());
         orderDTO.setStatus(order.getStatus());
         return orderDTO;
     }
 }
