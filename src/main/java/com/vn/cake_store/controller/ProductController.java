package com.vn.cake_store.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.service.ProductService;
import java.util.List;

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

     @GetMapping("/{id}")
     public ApiResponse<Product> getProductById(@PathVariable Long id) {
          return this.productService.getProductById(id);
     }

     @GetMapping
     public ApiResponse<List<Product>> getAllProduct() {
          return this.productService.getAllProduct();
     }

     @PutMapping("/{id}")
     public ApiResponse<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
          return this.productService.updateProduct(id, product);
     }

     @DeleteMapping("/{id}")
     public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
          return this.productService.deleteProductById(id);
     }
}
