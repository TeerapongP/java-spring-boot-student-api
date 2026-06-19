package com.avb.courseapi.controller.response;

// ErrorResponse ทำให้ error ที่ส่งกลับมีรูปแบบเดียวกันทุก endpoint
public record ErrorResponse(
        String code,
        String message,
        String description
) {
}
