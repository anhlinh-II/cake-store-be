package com.vn.cake_store.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.cake_store.dto.request.CreateReviewRequest;
import com.vn.cake_store.dto.request.UpdateReviewRequest;
import com.vn.cake_store.dto.response.ApiResponse;
import com.vn.cake_store.dto.response.ReviewResponse;
import com.vn.cake_store.entity.Product;
import com.vn.cake_store.entity.Review;
import com.vn.cake_store.mapper.ReviewMapper;
import com.vn.cake_store.service.ProductService;
import com.vn.cake_store.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewController {
     private final ReviewService reviewService;
     private final ReviewMapper reviewMapper;
     private final ProductService productService;

     @PostMapping
     public ApiResponse<ReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
          var review = this.reviewService.createReview(request);
          return ApiResponse.<ReviewResponse>builder()
                    .code(1000)
                    .message("Create review for product with ID" + request.getProductId() + " successfully!")
                    .result(reviewMapper.toReviewResponse(review))
                    .build();
     }

     @GetMapping("/{id}")
     public ApiResponse<ReviewResponse> getReviewById(@PathVariable Long id) {
          return ApiResponse.<ReviewResponse>builder()
                    .code(1000)
                    .message("Get review with ID " + id + " successfully!")
                    .result(reviewMapper.toReviewResponse(this.reviewService.getReviewById(id)))
                    .build();
     }

     @GetMapping
     public ApiResponse<List<ReviewResponse>> getAllReviews() {
          var allReviews = this.reviewService.getAllReivew();
          List<ReviewResponse> res = allReviews.stream().map(reviewMapper::toReviewResponse).toList();
          return ApiResponse.<List<ReviewResponse>>builder()
                    .code(1000)
                    .message("Get all reviews successfully!")
                    .result(res)
                    .build();
     }

     @PutMapping
     public ApiResponse<ReviewResponse> updateReivew(@RequestBody UpdateReviewRequest request) {
          return ApiResponse.<ReviewResponse>builder()
                    .code(1000)
                    .message("Update reivew with ID " + request.getReviewId() + " successfully!")
                    .result(reviewMapper.toReviewResponse(this.reviewService.updateReview(request)))
                    .build();
     }

     @DeleteMapping("/{id}")
     public ApiResponse<Void> deleteReview(@PathVariable Long id) {
          this.reviewService.deleteReview(id);
          return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Delete review with ID " + id + " successfully!")
                    .build();
     }

     @GetMapping("/product/{id}")
     public ApiResponse<Page<ReviewResponse>> getReviewsByProduct(
               @PathVariable Long id,
               @RequestParam(defaultValue = "0") int page,
               @RequestParam(defaultValue = "10") int size) {

          Product product = this.productService.findById(id).get();
          var listReviews = this.reviewService.getReviewsByProductId(product, page, size);
          var listReviewsResponse = listReviews.map(reviewMapper::toReviewResponse);
          
          return ApiResponse.<Page<ReviewResponse>>builder()
                    .code(1000)
                    .message("Get all reviews of product with ID " + id + " successfully!")
                    .result(listReviewsResponse)
                    .build();
     }
}
