package com.vn.cake_store.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class SupplierDTO {
     private Long supplierId;
     private String name;
     private String email;

     private List<Long> productsId;
}
