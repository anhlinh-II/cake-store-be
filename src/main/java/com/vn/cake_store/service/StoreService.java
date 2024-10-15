package com.vn.cake_store.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vn.cake_store.entity.Store;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
     private final StoreRepository storeRepository;

     Optional<Store> findById(Long id) {
          Optional<Store> optionalStore = this.storeRepository.findById(id);
          if (optionalStore.isEmpty()) {
               throw new AppException(ErrorCode.STORE_NOT_EXISTED);
          }
          return optionalStore;
     }
}
