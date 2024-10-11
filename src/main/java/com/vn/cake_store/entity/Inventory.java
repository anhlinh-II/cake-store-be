package com.vn.cake_store.entity;

import java.time.Instant;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inventories")
public class Inventory {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long inventoryId;

     private int quantity;

     private Instant updatedAt;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "store_id")
     private Store store;

     @ManyToMany(mappedBy = "inventories")
     private Set<Product> products;

     @PreUpdate
     public void handleBeforeUpdate() {
          this.updatedAt = Instant.now();
     }
}
