package com.avb.courseapi.controller.response;

import org.springframework.http.HttpStatus;

// BaseResponse คือรูปแบบกลางของ API response เพื่อให้ success/error มีโครงเหมือนกัน
public record BaseResponse<T>(
        boolean success,
        int status,
        String message,
        T data
) {

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, HttpStatus.OK.value(), "success", data);
    }

    public static <T> BaseResponse<T> created(T data) {
        return new BaseResponse<>(true, HttpStatus.CREATED.value(), "created", data);
    }

    public static <T> BaseResponse<T> error(HttpStatus status, String message) {
        return new BaseResponse<>(false, status.value(), message, null);
    }
}
