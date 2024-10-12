package com.vn.cake_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vn.cake_store.dto.request.CreateOrderDetailsRequest;
import com.vn.cake_store.dto.request.UpdateOrderDetailsRequest;
import com.vn.cake_store.dto.response.OrderDetailsResponse;
import com.vn.cake_store.entity.Order;
import com.vn.cake_store.entity.OrderDetails;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.mapper.OrderDetailsMapper;
import com.vn.cake_store.repository.OrderDetailsRepository;
import com.vn.cake_store.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
     private final OrderDetailsRepository orderDetailsRepository;
     private final ProductRepository productRepository;
     private final OrderService orderService;
     private final ProductService productService;

     public Optional<OrderDetails> findById(Long id) {
          Optional<OrderDetails> optionalOrderDetails = this.orderDetailsRepository.findById(id);
          if (optionalOrderDetails.isEmpty()) {
               throw new AppException(ErrorCode.ORDER_DETAILS_NOT_EXISTED);
          }
          return optionalOrderDetails;
     }

     public OrderDetails createOrderDetails(CreateOrderDetailsRequest orderDetailsReq) {
          // Find the order by its ID
          Order order = this.orderService.findById(orderDetailsReq.getOrderId()).get();

          // Get the set of product IDs from the request
          Long productId = orderDetailsReq.getProductId();

          // Fetch all products by their IDs
          Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

          boolean existRepeatedDetails = this.orderDetailsRepository.existsByProductAndOrder(product, order);

          if (existRepeatedDetails) {
               throw new AppException(ErrorCode.ORDER_DETAILS_EXISTED);
          }

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

     public OrderDetailsResponse getOrderDetailsById(Long id) {
          var orderDetails = this.findById(id).get();
          var res = OrderDetailsMapper.toOrderDetailsResponse(orderDetails);
          return res;
     }

     public void deleteOrderService(Long id) {
          OrderDetails dbDetails = this.findById(id).get();
          this.orderDetailsRepository.delete(dbDetails);
     }

     public OrderDetailsResponse updateOrderDetails(UpdateOrderDetailsRequest reqDetails) {
          var orderDetailsDb = this.findById(reqDetails.getOrderDetailsId())
                    .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAILS_NOT_EXISTED));

          var dbProduct = this.productService.findById(reqDetails.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

          var dbOrder = this.orderService.findById(reqDetails.getOrderId()).get();

          boolean existRepeatedDetails = this.orderDetailsRepository.existsByProductAndOrder(dbProduct, dbOrder);

          if (existRepeatedDetails) {
               throw new AppException(ErrorCode.ORDER_DETAILS_EXISTED);
          }

          orderDetailsDb.setOrder(dbOrder);
          orderDetailsDb.setPrice(reqDetails.getPrice());
          orderDetailsDb.setProduct(dbProduct);
          orderDetailsDb.setQuantity(reqDetails.getQuantity());

          this.orderDetailsRepository.save(orderDetailsDb);

          var res = OrderDetailsMapper.toOrderDetailsResponse(orderDetailsDb);

          return res;
     }
}
