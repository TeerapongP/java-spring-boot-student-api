package com.avb.courseapi.controller;

import com.avb.courseapi.controller.request.CreateCourseRequest;
import com.avb.courseapi.controller.request.UpdateCourseRequest;
import com.avb.courseapi.controller.response.BaseResponse;
import com.avb.courseapi.controller.response.CourseResponse;
import com.avb.courseapi.services.CourseService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// @RestController บอก Spring ว่า class นี้รับ HTTP request และคืนค่าเป็น JSON response
@RestController
// @RequestMapping กำหนด path หลักของ controller นี้ ทุก method ด้านล่างจะเริ่มด้วย /courses
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    // Dependency Injection: Spring จะส่ง CourseService เข้ามาให้ Controller ใช้งาน
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET /courses ใช้สำหรับดึงรายการ course ทั้งหมด
    @GetMapping("getCourses")
    public BaseResponse<List<CourseResponse>> getCourses() {
        return BaseResponse.success(courseService.getCourses());
    }

    // GET /courses/1 ค่า 1 จะถูกส่งเข้ามาที่ตัวแปร courseId ผ่าน @PathVariable
    @GetMapping("/{courseId}")
    public BaseResponse<CourseResponse> getCourse(@PathVariable Long courseId) {
        return BaseResponse.success(courseService.getCourse(courseId));
    }

    // POST /courses ใช้ @RequestBody เพื่อแปลง JSON body เป็น CreateCourseRequest
    @PostMapping("createCourse")
    // เมื่อสร้างข้อมูลสำเร็จ ควรคืน HTTP 201 Created
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CourseResponse> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return BaseResponse.created(courseService.createCourse(request));
    }

    // PATCH /courses/1 ใช้แก้ข้อมูลบาง field ของ course
    @PatchMapping("/{courseId}")
    public BaseResponse<CourseResponse> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseRequest request) {
        return BaseResponse.success(courseService.updateCourse(courseId, request));
    }

    // DELETE /courses/1 ใช้ลบ course ตาม id
    @DeleteMapping("/{courseId}")
    public BaseResponse<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return BaseResponse.success(null);
    }
}
