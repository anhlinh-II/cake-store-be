package com.vn.cake_store.dto.response;

import java.time.Instant;

import com.vn.cake_store.entity.constants.PaymentMethod;

import lombok.Data;

@Data
public class PaymentResponse {
     private Long paymentId;
     private Long orderId;
     private Instant date;
     private PaymentMethod method;
     private Double totalEarn;
}
