package com.aritra.healthbridge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DoctorRequestDTO(

        @NotBlank(message="Name is required")
         String name,
        @NotBlank(message="Degree is required")
         String degree,
        @NotBlank(message="Department is required")
         String department,
        @NotNull(message="Year of passing is required")
         LocalDate yearOfPassing,
        @NotBlank(message="Specialization is required")
         String specialization,
        @Email(message = "Please enter valid email Id")
        @NotBlank(message="Email is required")
         String email,
        @NotBlank(message="Phone number is required")
         String phone,
        @NotNull(message="consultation fee is required")
         Double consultationFee
) { }
