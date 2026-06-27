package com.avb.courseapi.controller.response;

// Response DTO คือรูปแบบข้อมูล JSON ที่ API ส่งกลับไปหา client
public record CourseResponse(
        Long id,
        String code,
        String title,
        String description
) {
}
