package com.vn.cake_store.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "suppliers")
@Entity
@Data
public class Supplier {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long supplierId;

     private String name;
     private String email;

     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "supplier")
     @JsonIgnore
     private List<Product> products = new ArrayList<>();

}
