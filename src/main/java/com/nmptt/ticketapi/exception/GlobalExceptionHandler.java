package com.nmptt.ticketapi.exception;

import com.nmptt.ticketapi.dto.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResponse<Void>> getResponse(HttpStatus status, String message) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(status.value())
                .message(message)
                .build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi hệ thống: " + e.getMessage());
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateDataException(DuplicateDataException e) {
        return getResponse(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException e) {
        return getResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return getResponse(HttpStatus.BAD_REQUEST,
                "Không thể xoá dữ liệu này vì đang bị ràng buộc bởi dữ liệu khác.\n" +
                        "Ví dụ: Nhân viên đã từng bán vé.");
    }
}
