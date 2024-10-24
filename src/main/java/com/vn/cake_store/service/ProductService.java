package com.vn.cake_store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

import com.vn.cake_store.dto.ProductDTO;
import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Supplier;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.ProductRepository;
import com.vn.cake_store.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final ProductRepository productRepository;
     private final SupplierRepository supplierRepository;

     public Optional<Product> findById(Long id) {
          var optionalProd = this.productRepository.findById(id);
          if (optionalProd.isEmpty()) {
               throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
          }
          return optionalProd;
     }

     public boolean isExistsByName(String name) {
          return this.productRepository.existsByName(name);
     }

     public List<Product> findByListProductId(List<Long> idsList) {
          return this.productRepository.findByProductIdIn(idsList);
     }

     public Product createProduct(ProductDTO productDTO) {
          var isExistedName = this.isExistsByName(productDTO.getName());

          if (isExistedName) {
               throw new AppException(ErrorCode.PRODUCT_EXISTED);
          }

          var supplier = this.supplierRepository.findById(productDTO.getSupplierId()).get();

          Product product = new Product();
          product.setName(productDTO.getName());
          product.setDescription(productDTO.getDescription());
          product.setCategory(productDTO.getCategory());
          product.setPrice(productDTO.getPrice());
          product.setStockQuantity(productDTO.getStockQuantity());
          product.setSoldQuantity(productDTO.getSoldQuantity());
          product.setSupplier(supplier);

          supplier.getProducts().add(product);

          return this.productRepository.save(product);
     }

     public Product getProductById(Long id) {
          return this.findById(id).get();
     }

     public Page<Product> getAllProduct(int page, int size) {
          Pageable pageable = PageRequest.of(page, size);
          return productRepository.findAll(pageable);
     }

     public Product updateProduct(ProductDTO product) {
          // check if supplier exist or not
          Supplier supplier = this.supplierRepository.findById(product.getSupplierId()).get();

          Product dbProduct = this.findById(product.getProductId()).get();

          dbProduct.setName(product.getName());
          dbProduct.setDescription(product.getDescription());
          dbProduct.setPrice(product.getPrice());
          dbProduct.setStockQuantity(product.getStockQuantity());
          dbProduct.setSoldQuantity(product.getSoldQuantity());
          dbProduct.setCategory(product.getCategory());
          dbProduct.setSupplier(supplier);

          supplier.getProducts().add(dbProduct);

          return this.productRepository.save(dbProduct);
     }

     public void deleteProductById(Long id) {
          this.productRepository.delete(this.findById(id).get());
     }
}
