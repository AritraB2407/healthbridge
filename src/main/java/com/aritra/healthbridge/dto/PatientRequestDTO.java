package com.aritra.healthbridge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PatientRequestDTO(
        @NotBlank(message="Name is required")
        String name,
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        String email,
        String bloodGroup,
        String phone,
        LocalDate dateOfBirth,
        @NotBlank(message = "Gender cannot be blank")
        String gender
) {}
