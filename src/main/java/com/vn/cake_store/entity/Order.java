package com.vn.cake_store.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.vn.cake_store.entity.constants.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

     @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
     private List<OrderDetails> orderDetailsList;
}
