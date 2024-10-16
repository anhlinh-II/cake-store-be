package com.vn.cake_store.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.vn.cake_store.dto.request.CreateStoreRequest;
import com.vn.cake_store.dto.request.UpdateStoreRequest;
import com.vn.cake_store.entity.Store;
import com.vn.cake_store.entity.constants.Role;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.EmployeeRepository;
import com.vn.cake_store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
     private final StoreRepository storeRepository;
     private final EmployeeRepository employeeRepository;

     public boolean isExistsByName(String name) {
          return this.storeRepository.existsByName(name);
     }

     public boolean isExistsByManagerId(Long id) {
          return this.storeRepository.existsByManager(this.findById(id).get().getManager());
     }

     public Optional<Store> findById(Long id) {
          Optional<Store> optionalStore = this.storeRepository.findById(id);
          if (optionalStore.isEmpty()) {
               throw new AppException(ErrorCode.STORE_NOT_EXISTED);
          }
          return optionalStore;
     }

     public Store createStore(CreateStoreRequest request) {
          var optionalManager = this.employeeRepository.findById(request.getManagerId());
          if (optionalManager.isEmpty()) {
               throw new AppException(ErrorCode.EMPLOYEE_NOT_EXISTED);
          }
          if (optionalManager.get().getRole() != Role.MANAGER) {
               throw new  AppException(ErrorCode.ISNOT_MANAGER);
          }

          if (this.isExistsByName(request.getName())) {
               throw new AppException(ErrorCode.STORE_EXISTED);
          }
          Store store = new Store();
          store.setName(request.getName());
          store.setLocation(request.getLocation());
          store.setManager(optionalManager.get());

          return this.storeRepository.save(store);
     }

     public Store getStoreById(Long id) {
          return this.findById(id).get();
     }

     public List<Store> getAllStore() {
          return this.storeRepository.findAll();
     }

     public Store updateStore(UpdateStoreRequest request) {
          log.info("id {}", request.getStoreId());
          var dbStore = this.findById(request.getStoreId()).get();
          if (this.isExistsByName(request.getName())) {
               throw new AppException(ErrorCode.STORE_EXISTED);
          }
          if (this.isExistsByManagerId(request.getManagerId()) && request.getManagerId() != dbStore.getManager().getEmployeeId()) {
               throw new AppException(ErrorCode.MANAGER_IN_USED);
          }
          dbStore.setName(request.getName());
          dbStore.setLocation(request.getLocation());
          dbStore.setManager(this.employeeRepository.findById(request.getManagerId()).get());

          return this.storeRepository.save(dbStore);
     }

     public void deleteStore(Long id) {
          this.storeRepository.delete(this.findById(id).get());
     }
     
}
