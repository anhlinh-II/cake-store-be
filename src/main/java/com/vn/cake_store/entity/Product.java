package com.vn.cake_store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.cake_store.entity.constants.Category;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

     @OneToMany(mappedBy = "product")
     @JsonIgnore
     private Set<OrderDetails> orderDetails;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "supplier_id") // Foreign key in products table
     private Supplier supplier;

     @ManyToMany
     @JoinTable(name = "product_inventory", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "inventory_id"))
     private Set<Inventory> inventories;

     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private Set<Review> reviews;
}
