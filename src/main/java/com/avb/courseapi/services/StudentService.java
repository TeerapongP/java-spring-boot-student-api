package com.avb.courseapi.services;

import com.avb.courseapi.controller.request.CreateStudentRequest;
import com.avb.courseapi.controller.response.StudentResponse;
import com.avb.courseapi.exception.ValidationException;
import com.avb.courseapi.outbound.repository.StudentRepository;
import com.avb.courseapi.outbound.repository.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentResponse createStudent(CreateStudentRequest request) {
        if(studentRepository.existsByStudentCode(request.studentCode())){
            throw new ValidationException("student code already exists");
        }

        Student student = new Student();
        student.setStudentCode(request.studentCode());
        student.setFullName(request.fullName());
        student.setEmail(request.email());
        student.setYear(request.year());
        student.setMajor(request.major());
        student.setStatus(request.status());

        Student savedStudent = studentRepository.save(student);
        return StudentResponse.from(savedStudent);
    }

}
