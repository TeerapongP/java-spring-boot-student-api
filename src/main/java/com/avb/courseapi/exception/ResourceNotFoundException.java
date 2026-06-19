package com.avb.courseapi.exception;

// ใช้โยนเมื่อ client ขอข้อมูลที่ไม่มีอยู่ในระบบ
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
