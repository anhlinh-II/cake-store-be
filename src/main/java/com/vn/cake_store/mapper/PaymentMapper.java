package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.response.PaymentResponse;
import com.vn.cake_store.entity.Payment;

@Component
public class PaymentMapper {
     public PaymentResponse toPaymentResponse(Payment payment) {
          PaymentResponse paymentResponse = new PaymentResponse();
          paymentResponse.setPaymentId(payment.getPaymentId());
          paymentResponse.setDate(payment.getDate());
          paymentResponse.setMethod(payment.getMethod());
          paymentResponse.setOrderId(payment.getOrder().getOrderId());
          paymentResponse.setTotalEarn(payment.getTotalEarn());

          return paymentResponse;
     }
}
