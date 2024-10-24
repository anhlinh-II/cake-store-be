package com.vn.cake_store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
     UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
     INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
     USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
     USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
     INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
     USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
     UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
     UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
     INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
     PRODUCT_EXISTED(1009, "Product existed", HttpStatus.BAD_REQUEST),
     PRODUCT_NOT_EXISTED(1010, "Product not existed", HttpStatus.BAD_REQUEST),
     ORDER_DETAILS_EXISTED(1011, "This order details already existed (repeated order and product)", HttpStatus.BAD_REQUEST),
     ORDER_DETAILS_NOT_EXISTED(1012, "This order details doesn't exist", HttpStatus.BAD_REQUEST),
     ORDER_NOT_EXISTED(1013, "This order don't exist, please check again", HttpStatus.BAD_REQUEST),
     PAYMENT_NOT_EXISTED(1014, "This payment don't existed", HttpStatus.BAD_REQUEST),
     PAYMENT_EXISTED(1015, "Payment existed, this order already has another payment", HttpStatus.BAD_REQUEST),
     REVIEW_NOT_EXISTED(1016, "Review doesn't exist", HttpStatus.BAD_REQUEST),
     REVIEW_EXISTED(1017, "This customer has already had a review on this product", HttpStatus.BAD_REQUEST),
     SUPPLIER_EXISTED(1018, "this supplier has already existed by name or email", HttpStatus.BAD_REQUEST),
     SUPPLIER_NOT_EXISTED(1019, "this supplier doesn't exist", HttpStatus.BAD_REQUEST), 
     STORE_NOT_EXISTED(1020, "This store doesn't exist", HttpStatus.BAD_REQUEST), 
     EMPLOYEE_NOT_EXISTED(1021, "This employee doesn't exist", HttpStatus.BAD_REQUEST), 
     ISNOT_MANAGER(1022, "This employee is not a manager", HttpStatus.BAD_REQUEST), 
     STORE_EXISTED(1023, "This store has already existed", HttpStatus.BAD_REQUEST), 
     MANAGER_IN_USED(1024, "This manager has already worked in another store", HttpStatus.BAD_REQUEST)
     ;

     ErrorCode(int code, String message, HttpStatusCode statusCode) {
          this.code = code;
          this.message = message;
          this.statusCode = statusCode;
     }

     private final int code;
     private final String message;
     private final HttpStatusCode statusCode;
}
