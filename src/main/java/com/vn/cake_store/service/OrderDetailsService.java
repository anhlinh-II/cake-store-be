package com.vn.cake_store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.cake_store.dto.request.CreateOrderDetailsRequest;
import com.vn.cake_store.entity.Order;
import com.vn.cake_store.entity.OrderDetails;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.OrderDetailsRepository;
import com.vn.cake_store.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
     private final OrderDetailsRepository orderDetailsRepository;
     private final ProductRepository productRepository;
     private final OrderService orderService;

     public OrderDetails createOrderDetails(CreateOrderDetailsRequest orderDetailsReq) {
          // Find the order by its ID
          Order order = this.orderService.findById(orderDetailsReq.getOrderId());

          // Get the set of product IDs from the request
          Long productId = orderDetailsReq.getProductId();

          // Fetch all products by their IDs
          Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

          OrderDetails orderDetails = new OrderDetails();
          orderDetails.setOrder(order);
          orderDetails.setPrice(orderDetailsReq.getPrice());
          orderDetails.setProduct(product);
          orderDetails.setQuantity(orderDetailsReq.getQuantity());

          return this.orderDetailsRepository.save(orderDetails);
     }

     public List<OrderDetails> getAllOrderDetails() {
          return this.orderDetailsRepository.findAll();
     }
}
