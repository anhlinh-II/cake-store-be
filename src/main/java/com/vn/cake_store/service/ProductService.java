package com.vn.cake_store.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final ProductRepository productRepository;

     public Optional<Product> findById(Long id) {
          return this.productRepository.findById(id);
     }

     public ApiResponse<Product> createProduct(Product product) {
          Optional<Product> optionalProduct = this.productRepository.findByName(product.getName());
          if (optionalProduct.isPresent()) {
               throw new AppException(ErrorCode.PRODUCT_EXISTED);
          }
          this.productRepository.save(product);
          return ApiResponse.<Product>builder()
                    .code(1000)
                    .message("Create product successfully!")
                    .result(product)
                    .build();
     }

     public ApiResponse<Product> getProductById(Long id) {
          Optional<Product> optionalProduct = this.findById(id);
          if (optionalProduct.isEmpty()) {
               throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
          }
          
          return ApiResponse.<Product>builder()
          .code(1000)
          .message("Get product with id " + id + " successfully!")
          .result(optionalProduct.get())
          .build();
     }
}
