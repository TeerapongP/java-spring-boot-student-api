package com.avb.courseapi.exception;

// ใช้โยนเมื่อ request ถูก format แล้ว แต่ผิด business rule ของระบบ
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
