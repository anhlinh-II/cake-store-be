package com.vn.cake_store.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.vn.cake_store.dto.request.CreateStoreRequest;
import com.vn.cake_store.dto.request.UpdateStoreRequest;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.StoreResponse;
import com.vn.cake_store.mapper.StoreMapper;
import com.vn.cake_store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
     private final StoreService storeService;
     private final StoreMapper storeMapper;

     @PostMapping
     public ApiResponse<StoreResponse> createStore(@RequestBody CreateStoreRequest request) {
          return ApiResponse.<StoreResponse>builder()
                    .code(1000)
                    .message("Create a store successfully!")
                    .result(storeMapper.toStoreResponse(this.storeService.createStore(request)))
                    .build();
     }

     @GetMapping("{id}")
     public ApiResponse<StoreResponse> getStoreById(@PathVariable Long id) {
          return ApiResponse.<StoreResponse>builder()
                    .code(1000)
                    .message("Get store with ID " + id + " successfully!")
                    .result(storeMapper.toStoreResponse(this.storeService.getStoreById(id)))
                    .build();
     }

     @GetMapping
     public ApiResponse<List<StoreResponse>> getAllStores() {
          var allStores = this.storeService.getAllStore();
          var res = allStores.stream().map(storeMapper::toStoreResponse).toList();
          return ApiResponse.<List<StoreResponse>>builder()
                    .code(1000)
                    .message("Get all stores successfully!")
                    .result(res)
                    .build();
     }

     @PutMapping
     public ApiResponse<StoreResponse> updateStore(@RequestBody UpdateStoreRequest request) {
          return ApiResponse.<StoreResponse>builder()
                    .code(1000)
                    .message("Update store with ID " + request.getStoreId() + " successfully!")
                    .result(storeMapper.toStoreResponse(this.storeService.updateStore(request)))
                    .build();
     }

     @DeleteMapping("{id}")
     public ApiResponse<Void> deleteStore(@PathVariable Long id) {
          this.storeService.deleteStore(id);
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete store with ID " + id + " successfully!")
                    .build();
     }
}
