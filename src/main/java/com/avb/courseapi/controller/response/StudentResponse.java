package com.avb.courseapi.controller.response;
import com.avb.courseapi.model.StudentStatus;
import com.avb.courseapi.outbound.repository.entity.Student;

// Response DTO คือรูปแบบข้อมูล JSON ที่ API ส่งกลับไปหา client
public record StudentResponse(
        Long id,
        String studentCode,
        String fullName,
        String email,
        Integer year,
        String major,
        StudentStatus status
) {
    public static StudentResponse from(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getStudentCode(),
                student.getFullName(),
                student.getEmail(),
                student.getYear(),
                student.getMajor(),
                student.getStatus()
        );
    }
}
