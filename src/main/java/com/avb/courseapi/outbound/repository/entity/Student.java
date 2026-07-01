package com.avb.courseapi.outbound.repository.entity;

import com.avb.courseapi.model.StudentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentCode;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column()
    private Integer year;

    @Column(nullable = false)
    private String major;

    @Enumerated(EnumType.STRING)
    @Column()
    @NotNull(message = "status is required")
    private StudentStatus status;

}
