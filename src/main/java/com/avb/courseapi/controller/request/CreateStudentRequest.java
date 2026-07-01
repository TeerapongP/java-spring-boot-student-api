package com.avb.courseapi.controller.request;

import com.avb.courseapi.model.StudentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStudentRequest(
        @NotBlank String studentCode,
        @NotBlank String fullName,
        @NotBlank @Email String email,
        @NotNull @Min(1) @Max(4) Integer year,
        @NotBlank String major,
        @NotNull StudentStatus status
) {
}
