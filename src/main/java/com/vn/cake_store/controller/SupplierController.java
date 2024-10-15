package com.vn.cake_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.request.SupplierDTO;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.mapper.SupplierMapper;
import com.vn.cake_store.service.SupplierService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/suppliers")
@RestController
@RequiredArgsConstructor
public class SupplierController {
     private final SupplierService supplierService;
     private final SupplierMapper supplierMapper;

     @PostMapping
     public ApiResponse<SupplierDTO> createSupplier(@RequestBody SupplierDTO request) {
          return ApiResponse.<SupplierDTO>builder()
                    .code(1000)
                    .message("Create a supplier successfully!")
                    .result(supplierMapper.toSupplierDTO(this.supplierService.createSupplier(request)))
                    .build();
     }

     @GetMapping("/{id}")
     public ApiResponse<SupplierDTO> getSupplierById(@PathVariable Long id) {
          return ApiResponse.<SupplierDTO>builder()
                    .code(1000)
                    .message("Get supplier with ID " + id + " successfully!")
                    .result(supplierMapper.toSupplierDTO(this.supplierService.getSupplierById(id)))
                    .build();
     }

     @GetMapping
     public ApiResponse<List<SupplierDTO>> getAllSupplier() {
          var allSupplier = this.supplierService.getAllSupplier();
          var allSupplierDTO = allSupplier.stream().map(supplierMapper::toSupplierDTO).toList();

          return ApiResponse.<List<SupplierDTO>>builder()
                    .code(1000)
                    .message("Get all supplier successfully!")
                    .result(allSupplierDTO)
                    .build();
     }

     @PutMapping
     public ApiResponse<SupplierDTO> updateSupplier(@RequestBody SupplierDTO request) {
          return ApiResponse.<SupplierDTO>builder()
                    .code(1000)
                    .message("Update supplier with ID " + request.getSupplierId() + " successfully!")
                    .result(supplierMapper.toSupplierDTO(this.supplierService.updateSupplier(request)))
                    .build();
     }

     @DeleteMapping("{id}")
     public ApiResponse<Void> deleteSupplier(@PathVariable Long id) {
          this.supplierService.deleteSupplier(id);
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete supplier with ID " + id + " successfully!")
                    .build();
     }
}
