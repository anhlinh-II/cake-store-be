package com.vn.cake_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vn.cake_store.dto.request.CreatePaymentRequest;
import com.vn.cake_store.dto.request.UpdatePaymentRequest;
import com.vn.cake_store.entity.Order;
import com.vn.cake_store.entity.Payment;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
     private final PaymentRepository paymentRepository;
     private final OrderService orderService;

     public Optional<Payment> findById(Long id) {
          var optionalPayment = this.paymentRepository.findById(id);
          if (optionalPayment.isEmpty()) {
               throw new AppException(ErrorCode.PAYMENT_NOT_EXISTED);
          }
          return optionalPayment;
     }

     public Payment findByOrderId(Long id) {
          var order = this.orderService.findById(id).get();
          return this.paymentRepository.findByOrder(order);
     }

     public Payment createPayment(CreatePaymentRequest reqPayment) {
          var order = this.orderService.findById(reqPayment.getOrderId()).get();

          Payment dbPayment = this.findByOrderId(reqPayment.getOrderId());

          if (dbPayment != null) {
               throw new AppException(ErrorCode.PAYMENT_EXISTED);
          }

          Payment payment = new Payment();
          payment.setOrder(order);
          payment.setMethod(reqPayment.getMethod());

          return this.paymentRepository.save(payment);
     }

     public Payment getPaymentById(Long id) {
          return this.findById(id).get();
     }

     public List<Payment> getAllPayment() {
          return this.paymentRepository.findAll();
     }

     public Payment updatePayment(UpdatePaymentRequest reqPayment) {
          // check if exist by paymentId
          Payment dbPayment = this.findById(reqPayment.getPaymentId()).get();

          // check if exist by orderId
          Payment dbPaymentById = this.findByOrderId(reqPayment.getOrderId());

          if (dbPaymentById != null && dbPayment != dbPaymentById) {
               throw new AppException(ErrorCode.PAYMENT_EXISTED);
          }

          Order order = this.orderService.findById(reqPayment.getOrderId()).get();

          dbPayment.setMethod(reqPayment.getMethod());
          dbPayment.setOrder(order);

          return this.paymentRepository.save(dbPayment);
     }

     public void deletePayment(Long id) {
          this.paymentRepository.delete(this.findById(id).get());
     }
}
