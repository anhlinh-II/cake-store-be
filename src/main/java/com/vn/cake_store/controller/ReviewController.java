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
     public ApiResponse<Page<ReviewResponse>> getAllReviews(
               @RequestParam(defaultValue = "0") int page, // Default to page 0
               @RequestParam(defaultValue = "10") int size // Default to 10 items per page
     ) {
          var allReviews = this.reviewService.getAllReivew(page, size);
          Page<ReviewResponse> res = allReviews.map(reviewMapper::toReviewResponse);
          return ApiResponse.<Page<ReviewResponse>>builder()
                    .code(1000)
                    .message("Get all reviews successfully!")
                    .result(res)
                    .build();
     }

     @PutMapping
     public ApiResponse<ReviewResponse> updateReivew(@RequestBody UpdateReviewRequest request) {
          return ApiResponse.<ReviewResponse>builder()
                    .code(1000)
               //      myse.atc
               //      irecc ierer(12)
               //      constrainsensor10150 -> 10
               //      khai báo baud 9600
               //      không thuộc digital io -> serial
               //      siêu âm archino pulchino ECHO -> độ rộng xung
               //      ITU iot là cơ sở hạ tầng toàn cầu
               //      ITO là mạng lướt gatermer
               //      độ nhạy -> sự thay đổi toisi tjieeur
               //      có độ phân giải cao
               //      quan sát một hoặc nhiều thuộc tính
               //      rpl không hỗ trợ phương thức đa điểm đến Mp2p
               //      o oab servmana app busi
               //      giao tiếp với các thiết bị lớp 1
               //      IPv6 ko phải định danh
               //      lớp quản lý dihcj vu -> cho phép làm việc với các đối tuongwk không đồng nhất
               // NFC không phải nền tảng phần cứng phổ biến
               // không phải hệ điều hành phổ biến FPGA
               // thành phần ngữ nghĩa giảm kích thước lưu trữ -> xml nhị nphaan
               // hàm chiếu 3 lớp không bao gồm lớp kêtnoois
               // mdns không thuộc giao thức ứng dụng
               // gxmpp hông thuộc cơ sở hạ tầng
               // coap chia 2 lớp nhắn tin/ phả hồi
               // có thể bị đánh cắp dữ liệu
               // số lượng thiết bị mạng khoogn phải là tiêu chí
               // z-wave 30met
               // cung capp bảo mật mã hóa xác thực
               // administraoter

               // pb1 đúng 2 sai
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
