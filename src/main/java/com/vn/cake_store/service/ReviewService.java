package com.vn.cake_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vn.cake_store.dto.request.CreateReviewRequest;
import com.vn.cake_store.dto.request.UpdateReviewRequest;
import com.vn.cake_store.entity.Customer;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Review;
import com.vn.cake_store.exception.AppException;
import com.vn.cake_store.exception.ErrorCode;
import com.vn.cake_store.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
     private final ReviewRepository reviewRepository;
     private final ProductService productService;
     private final CustomerService customerService;

     public Optional<Review> findById(Long id) {
          var optionalReview = this.reviewRepository.findById(id);
          if (optionalReview.isEmpty()) {
               throw new AppException(ErrorCode.REVIEW_NOT_EXISTED);
          }
          return optionalReview;
     }

     public boolean isExistsByCustomerAndProduct(Product product, Customer customer) {
          return this.reviewRepository.existsByProductAndCustomer(product, customer);
     }

     // public List<Review> getAllByProduct

     // CRUD

     public Review createReview(CreateReviewRequest reqReview) {
          var product = this.productService.findById(reqReview.getProductId()).get();
          var customer = this.customerService.findById(reqReview.getCustomerId()).get();

          boolean isExistedReivew = this.isExistsByCustomerAndProduct(product, customer);

          if (isExistedReivew) {
               throw new AppException(ErrorCode.REVIEW_EXISTED);
          }

          Review review = new Review();
          review.setCustomer(customer);
          review.setProduct(product);
          review.setComment(reqReview.getComment());
          review.setRating(reqReview.getRating());

          return this.reviewRepository.save(review);
     }

     public Review getReviewById(Long id) {
          return this.findById(id).get();
     }

     public List<Review> getAllReivew() {
          return this.reviewRepository.findAll();
     }

     public Review updateReview(UpdateReviewRequest reqReview) {
          Review dbReview = this.findById(reqReview.getReviewId()).get();

          dbReview.setComment(reqReview.getComment());
          dbReview.setRating(reqReview.getRating());

          return this.reviewRepository.save(dbReview);
     }

     public void deleteReview(Long id) {
          this.reviewRepository.delete(this.findById(id).get());
     }

     public List<Review> getReviewsByProductId(Long id) {
          var product = this.productService.findById(id).get();

          return this.reviewRepository.findAllByProduct(product);
     }
}
