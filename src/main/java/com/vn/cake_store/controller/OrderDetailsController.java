package com.vn.cake_store.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.request.CreateOrderDetailsRequest;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.OrderDetailsResponse;
import com.vn.cake_store.entity.OrderDetails;
import com.vn.cake_store.mapper.OrderDetailsMapper;
import com.vn.cake_store.service.OrderDetailsService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orderDetails")
public class OrderDetailsController {
     private final OrderDetailsService orderDetailsService;

     @PostMapping
     public ApiResponse<OrderDetailsResponse> createOrderDetails(@RequestBody CreateOrderDetailsRequest createReq) {
          OrderDetails orderDetails = this.orderDetailsService.createOrderDetails(createReq);
          var res = OrderDetailsMapper.toOrderDetailsResponse(orderDetails);
          return ApiResponse.<OrderDetailsResponse>builder()
                    .code(1000)
                    .message("Create details successfully")
                    .result(res)
                    .build();
     }

     @GetMapping()
     public ApiResponse<List<OrderDetailsResponse>> getAllOrderDetails() {
          var allOrderDetails = this.orderDetailsService.getAllOrderDetails();
          var allOrderDetailsRes = allOrderDetails
                    .stream()
                    .map(OrderDetailsResponse::new)
                    .collect(Collectors.toList());
          return ApiResponse.<List<OrderDetailsResponse>>builder()
                    .code(1000)
                    .message("Get all order details successfully!")
                    .result(allOrderDetailsRes)
                    .build();
     }

}
