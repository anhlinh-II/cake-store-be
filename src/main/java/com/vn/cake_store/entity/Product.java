package com.vn.cake_store.entity;

import com.vn.cake_store.entity.constants.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long productId;

     private String name;

     @Column(columnDefinition = "TEXT")
     private String description;

     private Double price;

     private int stockQuantity;

     private int soldQuantity;

     private Category category;
}
