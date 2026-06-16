# Course API Workshop

Starter project สำหรับสอน **Backend Development with Java Spring Boot** แบบทำไปพร้อมนักศึกษา

โจทย์ของ workshop คือสร้าง API สำหรับจัดการโครงสร้างคอร์สเรียน:

```text
Course -> CourseModule -> Lesson -> ExamRoomBooking
```

ไฟล์ส่วนใหญ่ใน commit แรกตั้งใจให้เป็นไฟล์เปล่า เพื่อให้ผู้สอนค่อย ๆ เติม code ระหว่างสอน ไม่ใช่ให้ผู้เรียนเปิดมาเจอเฉลยทั้งหมดตั้งแต่ต้น

## Tech Stack

- Java 26
- Spring Boot 4.1.0
- Maven
- Spring Web
- Validation
- Spring Data JPA
- MySQL Driver
- Lombok

## Project Metadata

```text
Group: com.avb
Artifact: course-api
Name: course-api
Package name: com.avb.courseapi
Packaging: Jar
Java: 26
```

## Structure

```text
src/main/java/com/avb/courseapi/
├── CourseApiApplication.java
├── config/
├── controller/
│   ├── CourseController.java
│   ├── request/
│   │   ├── CreateCourseRequest.java
│   │   ├── CreateCourseModuleRequest.java
│   │   ├── CreateLessonRequest.java
│   │   └── CreateExamRoomBookingRequest.java
│   └── response/
│       ├── CourseResponse.java
│       ├── CourseModuleResponse.java
│       ├── LessonResponse.java
│       ├── ExamRoomBookingResponse.java
│       └── ErrorResponse.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── ValidationException.java
├── model/
│   ├── ContentStatus.java
│   └── ExamBookingStatus.java
├── outbound/
│   └── repository/
│       ├── CourseRepository.java
│       ├── CourseModuleRepository.java
│       ├── LessonRepository.java
│       ├── ExamRoomBookingRepository.java
│       └── entity/
│           ├── Course.java
│           ├── CourseModule.java
│           ├── Lesson.java
│           └── ExamRoomBooking.java
├── security/
├── services/
│   └── CourseService.java
└── util/
```

## Domain Model

| Entity | Table | หน้าที่ |
| --- | --- | --- |
| `Course` | `courses` | คอร์สเรียน เช่น Java Programming |
| `CourseModule` | `course_modules` | หัวข้อหลักในคอร์ส |
| `Lesson` | `lessons` | หัวข้อย่อยในหัวข้อหลัก |
| `ExamRoomBooking` | `exam_room_bookings` | วันสอบและห้องสอบของ lesson |

Relationship:

```text
Course 1:N CourseModule
CourseModule 1:N Lesson
Lesson 1:N ExamRoomBooking
```

## Workshop Flow

1. อธิบาย Project Metadata และ Dependencies
2. อธิบาย Spring Boot main class
3. เติม Entity และ JPA annotations
4. เติม Repository
5. เติม Request DTO พร้อม Validation
6. เติม Response DTO
7. เติม Service
8. เติม Controller endpoints
9. เติม Exception Handling
10. ทดสอบ API ด้วย Postman หรือ Bruno

## MySQL Setup

```sql
CREATE DATABASE course_api;
```

## Run

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 26)
export PATH="$JAVA_HOME/bin:$PATH"
./mvnw spring-boot:run
```
