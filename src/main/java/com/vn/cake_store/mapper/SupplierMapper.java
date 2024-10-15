package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.request.SupplierDTO;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Supplier;

@Component
public class SupplierMapper {
     public SupplierDTO toSupplierDTO(Supplier supplier) {
          SupplierDTO dto = new SupplierDTO();
          dto.setSupplierId(supplier.getSupplierId());
          dto.setEmail(supplier.getEmail());
          dto.setName(supplier.getName());
          dto.setProductsId(supplier.getProducts().stream().map(Product::getProductId).toList());

          return dto;
     }
}
