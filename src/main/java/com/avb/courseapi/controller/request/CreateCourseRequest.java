package com.avb.courseapi.controller.request;

import jakarta.validation.constraints.NotBlank;

// Request DTO คือรูปแบบข้อมูล JSON ที่ client ส่งเข้ามาตอนสร้าง course
public record CreateCourseRequest(
        // @NotBlank ใช้ validate ว่าห้ามเป็น null, ค่าว่าง, หรือมีแต่ช่องว่าง
        @NotBlank(message = "code is required")
        String code,
        @NotBlank(message = "title is required")
        String title,
        String description
) {
}
