package com.avb.courseapi.controller;

import com.avb.courseapi.controller.request.CreateCourseRequest;
import com.avb.courseapi.controller.request.CreateStudentRequest;
import com.avb.courseapi.controller.request.UpdateCourseRequest;
import com.avb.courseapi.controller.response.BaseResponse;
import com.avb.courseapi.controller.response.CourseResponse;
import com.avb.courseapi.controller.response.StudentResponse;
import com.avb.courseapi.services.CourseService;
import com.avb.courseapi.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final StudentService studentService;
    private final CourseService courseService;

    public CourseController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/getCourses")
    public BaseResponse<List<CourseResponse>> getCourses() {
        return BaseResponse.success(courseService.getCourses());
    }

    @GetMapping("/{courseId}")
    public BaseResponse<CourseResponse> getCourse(@PathVariable Long courseId) {
        return BaseResponse.success(courseService.getCourse(courseId));
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<StudentResponse> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        return BaseResponse.created(studentService.createStudent(request));
    }

    @PostMapping("/createCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CourseResponse> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return BaseResponse.created(courseService.createCourse(request));
    }

    @PatchMapping("/{courseId}")
    public BaseResponse<CourseResponse> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseRequest request) {
        return BaseResponse.success(courseService.updateCourse(courseId, request));
    }

    @DeleteMapping("/{courseId}")
    public BaseResponse<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return BaseResponse.success(null);
    }
}