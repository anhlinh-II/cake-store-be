package com.vn.cake_store.entity;

import java.time.Instant;
import java.time.LocalDateTime;

import com.vn.cake_store.entity.constants.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "payments")
@Entity
@Data
public class Payment {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long paymentId;

     @OneToOne
     @JoinColumn(name = "order_id", referencedColumnName = "orderId")
     private Order order;

     private Instant date;

     @Enumerated(EnumType.STRING)
     private PaymentMethod method;

     private Double totalEarn;

     @PrePersist
     public void handleBeforePersist() {
          this.date = Instant.now();
     }
}
