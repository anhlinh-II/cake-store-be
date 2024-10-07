package com.vn.cake_store.entity;

import java.time.LocalDateTime;

import com.vn.cake_store.entity.constants.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Table(name = "orders")
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Order {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long orderId;

     @ManyToOne
     @JoinColumn(name = "customer_id")
     private Customer customer;

     private LocalDateTime orderDate;

     private OrderStatus status;
}
