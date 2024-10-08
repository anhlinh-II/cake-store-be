package com.vn.cake_store.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.vn.cake_store.dto.OrderDTO;
import com.vn.cake_store.entity.Order;
import com.vn.cake_store.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderMapper {
     @Autowired
     private static CustomerService customerService; 

     public static OrderDTO toOrderDTO(Order order) {
         OrderDTO orderDTO = new OrderDTO();
         orderDTO.setOrderId(order.getOrderId());
         orderDTO.setCustomerId(order.getCustomer().getCustomerId());
         orderDTO.setStatus(order.getStatus());
         orderDTO.setOrderDate(order.getOrderDate());
         return orderDTO;
     }

     public static Order toOrder(OrderDTO orderDTO) {
          var customer = OrderMapper.customerService.findById(orderDTO.getCustomerId());
          Order order = new Order();
          order.setCustomer(customer.get());
          order.setOrderDate(orderDTO.getOrderDate());
          order.setStatus(orderDTO.getStatus());
          order.setOrderId(orderDTO.getOrderId());
          return order;
     }
 }
