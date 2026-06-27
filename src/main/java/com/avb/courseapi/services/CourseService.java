package com.avb.courseapi.services;

import com.avb.courseapi.controller.request.CreateCourseRequest;
import com.avb.courseapi.controller.request.UpdateCourseRequest;
import com.avb.courseapi.controller.response.CourseResponse;
import com.avb.courseapi.exception.ResourceNotFoundException;
import com.avb.courseapi.exception.ValidationException;
import com.avb.courseapi.model.ContentStatus;
import com.avb.courseapi.outbound.repository.CourseRepository;
import com.avb.courseapi.outbound.repository.entity.Course;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

// @Service บอก Spring ว่า class นี้เป็น business layer และสามารถ inject ไปใช้ที่ Controller ได้
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    // Dependency Injection: Service ขอใช้ Repository โดยไม่ต้อง new เอง
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Service รับคำสั่งจาก Controller แล้วไปขอข้อมูลจาก Repository
    public List<CourseResponse> getCourses() {
        return courseRepository.findAll().stream()
                // DTO Design: แปลง Entity ทุกตัวให้เป็น Response DTO ก่อนส่งกลับไปที่ Controller
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getCode(),
                        course.getTitle(),
                        course.getDescription()
                ))
                .toList();
    }

    // Service เป็นที่รวม business flow เช่น หา course ถ้าไม่เจอก็โยน exception
    public CourseResponse getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course id " + courseId + " does not exist"));

        return new CourseResponse(
                course.getId(),
                course.getCode(),
                course.getTitle(),
                course.getDescription()
        );
    }

    // Request DTO ใช้รับข้อมูลจาก client แล้ว Service แปลงเป็น Entity เพื่อบันทึก
    public CourseResponse createCourse(CreateCourseRequest request) {
        if (courseRepository.existsByCode(request.code())) {
            throw new ValidationException("course code already exists");
        }

        Course course = new Course();
        course.setCode(request.code());
        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setLevel("BEGINNER");
        course.setStatus(ContentStatus.DRAFT);

        try {
            Course savedCourse = courseRepository.save(course);
            return new CourseResponse(
                    savedCourse.getId(),
                    savedCourse.getCode(),
                    savedCourse.getTitle(),
                    savedCourse.getDescription()
            );
        } catch (DataIntegrityViolationException ex) {
            // ใช้กันกรณี database reject เช่น unique constraint แม้ service จะเช็คไปแล้ว
            throw new ValidationException("course data is invalid or already exists");
        }
    }

    // PATCH: แก้เฉพาะ field ที่ client ส่งมา
    public CourseResponse updateCourse(Long courseId, UpdateCourseRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course id " + courseId + " does not exist"));

        if (request.code() != null) {
            course.setCode(request.code());
        }
        if (request.title() != null) {
            course.setTitle(request.title());
        }
        if (request.description() != null) {
            course.setDescription(request.description());
        }

        try {
            Course savedCourse = courseRepository.save(course);
            return new CourseResponse(
                    savedCourse.getId(),
                    savedCourse.getCode(),
                    savedCourse.getTitle(),
                    savedCourse.getDescription()
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ValidationException("course data is invalid or already exists");
        }
    }

    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course id " + courseId + " does not exist"));
        courseRepository.delete(course);
    }
}
