package com.vn.cake_store.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "stores")
public class Store {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long storeId;

     private String name;
     private String location;

     @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private Set<Inventory> inventories;

     @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private Set<Employee> employees; // All employees in the store

     @OneToOne
     @JoinColumn(name = "manager_id") // Foreign key in store table
     private Employee manager; // Reference to the manager
}
