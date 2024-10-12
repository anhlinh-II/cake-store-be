package com.vn.cake_store.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.request.CreatePaymentRequest;
import com.vn.cake_store.dto.request.UpdatePaymentRequest;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.PaymentResponse;
import com.vn.cake_store.mapper.PaymentMapper;
import com.vn.cake_store.service.PaymentService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentController {
     private final PaymentService paymentService;
     private final PaymentMapper paymentMapper;

     @PostMapping
     public ApiResponse<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
          var payment = this.paymentService.createPayment(request);
          return ApiResponse.<PaymentResponse>builder()
                    .code(1000)
                    .message("Create payment successfully!")
                    .result(paymentMapper.toPaymentResponse(payment))
                    .build();
     }

     @GetMapping("/{id}")
     public ApiResponse<PaymentResponse> getPaymentById(@PathVariable Long id) {
          return ApiResponse.<PaymentResponse>builder()
                    .code(1000)
                    .message("Get payment with ID " + id + " successfully!")
                    .result(paymentMapper.toPaymentResponse(this.paymentService.getPaymentById(id)))
                    .build();
     }

     @GetMapping
     public ApiResponse<List<PaymentResponse>> getAllPayment() {
          var allPayments = this.paymentService.getAllPayment();

          List<PaymentResponse> paymentResponses = allPayments.stream()
                    .map(paymentMapper::toPaymentResponse)
                    .collect(Collectors.toList());

          return ApiResponse.<List<PaymentResponse>>builder()
                    .code(1000)
                    .message("Get all payments successfully!")
                    .result(paymentResponses)
                    .build();
     }

     @PutMapping
     public ApiResponse<PaymentResponse> updatePayment(@RequestBody UpdatePaymentRequest request) {
          var payment = this.paymentService.updatePayment(request);
          return ApiResponse.<PaymentResponse>builder()
                    .code(1000)
                    .message("Update payment with ID " + request.getPaymentId() + " successfully!")
                    .result(paymentMapper.toPaymentResponse(payment))
                    .build();
     }

     @DeleteMapping("/{id}")
     public ApiResponse<Void> deletePayment(@PathVariable Long id) {
          this.paymentService.deletePayment(id);
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete payment with ID " + id + " successfully!")
                    .build();
     }

}
