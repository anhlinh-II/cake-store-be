package com.vn.cake_store.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.ProductDTO;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.mapper.ProductMapper;
import com.vn.cake_store.service.ProductService;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
     private final ProductService productService;
     private final ProductMapper productMapper;

     @PostMapping()
     public ApiResponse<ProductDTO> createProduct(@RequestBody ProductDTO reqProduct) {
          var product = this.productService.createProduct(reqProduct);
          return ApiResponse.<ProductDTO>builder()
                    .code(1000)
                    .message("Create a product successfully!")
                    .result(productMapper.toProductDTO(product))
                    .build();
     }

     @GetMapping("/{id}")
     public ApiResponse<ProductDTO> getProductById(@PathVariable Long id) {
          return ApiResponse.<ProductDTO>builder()
                    .code(1000)
                    .message("Get product with ID " + id + " successfully!")
                    .result(productMapper.toProductDTO(this.productService.getProductById(id)))
                    .build();
     }

     @GetMapping
     public ApiResponse<List<ProductDTO>> getAllProduct() {
          var allProduct = this.productService.getAllProduct();
          var allProductDTO = allProduct.stream().map(productMapper::toProductDTO).toList();
          return ApiResponse.<List<ProductDTO>>builder()
                    .code(1000)
                    .message("Get all product successfully!")
                    .result(allProductDTO)
                    .build();
     }

     @PutMapping()
     public ApiResponse<ProductDTO> updateProduct(@RequestBody ProductDTO product) {
          return ApiResponse.<ProductDTO>builder()
                    .code(1000)
                    .message("Update product with ID" + product.getProductId() + " successfully!")
                    .result(productMapper.toProductDTO(this.productService.updateProduct(product)))
                    .build();
     }

     @DeleteMapping("/{id}")
     public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
          this.productService.deleteProductById(id);
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete product with ID " + id + " successfully!")
                    .build();
     }
}
