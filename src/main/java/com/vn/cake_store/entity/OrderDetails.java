package com.vn.cake_store.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "order_details")
@Entity
@Data
public class OrderDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "order_details_id")
     private Long orderDetailsId;

     @ManyToOne
     @JoinColumn(name = "order_id", nullable = false)
     private Order order;

     @ManyToOne
     @JoinColumn(name = "product_id", nullable = false)
     private Product product;

     private Long quantity;
     private Double price;
}
