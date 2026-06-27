package com.avb.courseapi.outbound.repository;

import com.avb.courseapi.outbound.repository.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository มีหน้าที่คุยกับ database เท่านั้น ไม่ใส่ business logic ไว้ในชั้นนี้
// JpaRepository เตรียม method พื้นฐานให้แล้ว เช่น findAll, findById, save, deleteById
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Spring Data JPA สร้าง query จากชื่อ method: เช็คว่ามี code นี้อยู่แล้วหรือยัง
    boolean existsByCode(String code);
}
