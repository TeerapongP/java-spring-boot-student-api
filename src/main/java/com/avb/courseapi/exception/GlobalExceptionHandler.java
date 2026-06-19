package com.avb.courseapi.exception;

import com.avb.courseapi.controller.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice คือจุดกลางสำหรับจัดการ exception ที่เกิดจากทุก controller
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // จัดการกรณีหา resource ไม่เจอ เช่น GET /courses/999 แล้วไม่มีข้อมูล
    @ExceptionHandler(ResourceNotFoundException.class)
    // กำหนด HTTP status ที่ client จะได้รับ
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<Void> handleNotFound(ResourceNotFoundException ex) {
        LOGGER.warn("Resource not found: {}", ex.getMessage());
        return BaseResponse.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // จัดการ business validation เช่น ข้อมูลผิดกฎของระบบ
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> handleBusinessValidation(ValidationException ex) {
        LOGGER.warn("Business validation failed: {}", ex.getMessage());
        return BaseResponse.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // จัดการ validation จาก annotation เช่น @NotBlank ใน request DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> handleValidation(MethodArgumentNotValidException ex) {
        // ดึง error แรกออกมาให้ response อ่านง่ายสำหรับ workshop เบื้องต้น
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("request is invalid");
        LOGGER.warn("Request validation failed: {}", message);
        return BaseResponse.error(HttpStatus.BAD_REQUEST, message);
    }
}
