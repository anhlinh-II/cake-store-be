package com.vn.cake_store.service;

import org.springframework.stereotype.Service;

import java.util.*;

import com.vn.cake_store.dto.request.SupplierDTO;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Supplier;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
     private final SupplierRepository supplierRepository;
     private final ProductService productService;

     public Optional<Supplier> findById(Long supplierId) {
          var optionalSupplier = this.supplierRepository.findById(supplierId);
          if (optionalSupplier.isEmpty()) {
               throw new AppException(ErrorCode.SUPPLIER_NOT_EXISTED);
          }
          return optionalSupplier;
     }

     public boolean isExistsByNameOrEmail(String name, String email) {
          return this.supplierRepository.existsByNameOrEmail(name, email);
     }

     // CRUD

     public Supplier createSupplier(SupplierDTO reqSupplier) {
          boolean isExistedSupplier = this.isExistsByNameOrEmail(reqSupplier.getName(), reqSupplier.getEmail());

          if(isExistedSupplier) {
               throw new AppException(ErrorCode.SUPPLIER_EXISTED);
          }

          List<Product> alreadyExistProducts = this.productService.findByListProductId(reqSupplier.getProductsId());
          Supplier supplier = new Supplier();
          supplier.setEmail(reqSupplier.getEmail());
          supplier.setName(reqSupplier.getName());
          supplier.setProducts(alreadyExistProducts);

          return this.supplierRepository.save(supplier);
     }

     public Supplier getSupplierById(Long id) {
          return this.findById(id).get();
     }

     public List<Supplier> getAllSupplier() {
          return this.supplierRepository.findAll();
     }

     public Supplier updateSupplier(SupplierDTO request) {
          var dbSupplier = this.findById(request.getSupplierId()).get();

          boolean isExistedSupplier = this.isExistsByNameOrEmail(request.getName(), request.getEmail());

          if(isExistedSupplier) {
               throw new AppException(ErrorCode.SUPPLIER_EXISTED);
          }

          dbSupplier.setEmail(request.getEmail());
          dbSupplier.setName(request.getName());

          return this.supplierRepository.save(dbSupplier);
     }

     public void deleteSupplier(Long id) {
          this.supplierRepository.delete(this.findById(id).get());
     }
     
}
