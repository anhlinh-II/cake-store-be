package com.vn.cake_store.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {
     private final ProductService productService;

     @PostMapping()
     public ApiResponse<Product> createProduct(@RequestBody Product product) {
          return this.productService.createProduct(product);
     }

     public ApiResponse<Product> getProductById(@PathVariable Long id) {
          return this.productService.getProductById(id);
     }
}
