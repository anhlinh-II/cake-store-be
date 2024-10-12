package com.vn.cake_store.dto.request;

import com.vn.cake_store.entity.constants.PaymentMethod;

import lombok.Data;

@Data
public class UpdatePaymentRequest {
     private Long paymentId;
     private Long orderId;
     private PaymentMethod method;
}
