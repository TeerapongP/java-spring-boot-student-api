package com.avb.courseapi.controller.request;

// Patch request: ส่งมาเฉพาะ field ที่ต้องการแก้ ไม่จำเป็นต้องส่งครบทุก field
public record UpdateCourseRequest(
        String code,
        String title,
        String description
) {
}
