package com.avb.courseapi.controller.response;

public record ErrorResponse(
        String code,
        String message,
        String description
) {
}
