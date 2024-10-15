package com.vn.cake_store.dto;

import java.util.Set;

import com.vn.cake_store.entity.constants.Category;

import lombok.Data;

@Data
public class ProductDTO {
     private Long productId;
     private String name;
     private String description;

     private Double price;

     private int stockQuantity;

     private int soldQuantity;

     private Category category;

     private Long supplierId;

     
     private Set<Long> reviewsId;
}
