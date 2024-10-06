package com.vn.cake_store.service;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

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

     public ApiResponse<List<Product>> getAllProduct() {
          return ApiResponse.<List<Product>>builder()
                    .code(1000)
                    .message("Get all product succesfully!")
                    .result(this.productRepository.findAll())
                    .build();
     }

     public ApiResponse<Product> updateProduct(Long id, Product product) {
          Optional<Product> optionalProd = this.findById(id);
          if (optionalProd.isEmpty()) {
               throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
          }
          Product dbProduct = optionalProd.get();
          dbProduct.setName(product.getName());
          dbProduct.setDescription(product.getDescription());
          dbProduct.setPrice(product.getPrice());
          dbProduct.setStockQuantity(product.getStockQuantity());
          dbProduct.setSoldQuantity(product.getSoldQuantity());
          dbProduct.setCategory(product.getCategory());
          this.productRepository.save(dbProduct);
          return ApiResponse.<Product>builder()
                    .code(1000)
                    .message("Update product with id " + id + " successfully!")
                    .result(dbProduct)
                    .build();
     }

     public ApiResponse<Void> deleteProductById(Long id) {
          var prod = this.findById(id);
          if (prod.isEmpty()) {
               throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
          }
          this.productRepository.delete(prod.get());
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete product with id " + id + " successfully!")
                    .result(null)
                    .build();
     }
}
