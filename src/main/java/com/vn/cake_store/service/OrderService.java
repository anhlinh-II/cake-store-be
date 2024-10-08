package com.vn.cake_store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.vn.cake_store.dto.CreateOrderDTO;
import com.vn.cake_store.dto.OrderDTO;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.entity.Order;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.mapper.OrderMapper;
import com.vn.cake_store.repository.CustomerRepository;
import com.vn.cake_store.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
     private final OrderRepository orderRepository;
     private final CustomerService customerService;
     private final CustomerRepository customerRepository;

     @Transactional
     public ApiResponse<OrderDTO> createOrder(CreateOrderDTO createOrderDTO) {
          Customer customer = customerService.findById(createOrderDTO.getCustomerId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

          // create new order
          Order order = new Order();
          order.setCustomer(customer);
          order.setStatus(createOrderDTO.getStatus());

          // persist order to database
          this.orderRepository.save(order);

          var resOrderDTO = OrderMapper.toOrderDTO(order);
          
          return ApiResponse.<OrderDTO>builder()
                    .code(1000)
                    .message("Create an order successfully!")
                    .result(resOrderDTO)
                    .build();
     }

     public List<Order> getAllOrders() {
          return this.orderRepository.findAll();
     }

     @Transactional(readOnly = true)
     public List<Order> getOrderByCustomer(Long customerId) {

          Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

          return orderRepository.findAllByCustomer(customer);
     }

     public Order findById(Long id) {
          Order order = this.orderRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

          return order;
     }

     public Order getOrderById(Long id) {
          var order = this.findById(id);
          return order;
     }

     public Order updateOrder(OrderDTO reqOrderDTO) {
          // get order from db
          var dbOrder = this.findById(reqOrderDTO.getOrderId());
          // get user who owns this order from db
          var customer = customerService.findById(reqOrderDTO.getCustomerId());

          // Order order = new Order();
          dbOrder.setCustomer(customer.get());
          dbOrder.setOrderDate(reqOrderDTO.getOrderDate());
          dbOrder.setStatus(reqOrderDTO.getStatus());
          dbOrder.setOrderId(reqOrderDTO.getOrderId());

          return this.orderRepository.save(dbOrder);
     }
}
