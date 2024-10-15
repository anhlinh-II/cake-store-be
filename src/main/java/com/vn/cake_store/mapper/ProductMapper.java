package com.vn.cake_store.mapper;

import org.springframework.stereotype.Component;

import com.vn.cake_store.dto.ProductDTO;
import com.vn.cake_store.entity.Product;

@Component
public class ProductMapper {
     public ProductDTO toProductDTO(Product product) {
          ProductDTO dto = new ProductDTO();
          dto.setProductId(product.getProductId());
          dto.setName(product.getName());
          dto.setDescription(product.getDescription());
          dto.setCategory(product.getCategory());
          dto.setPrice(product.getPrice());
          dto.setStockQuantity(product.getStockQuantity());
          dto.setSoldQuantity(product.getSoldQuantity());
          dto.setSupplierId(product.getSupplier().getSupplierId());

          return dto;
     }
}
