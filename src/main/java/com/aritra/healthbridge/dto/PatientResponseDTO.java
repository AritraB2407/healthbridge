package com.aritra.healthbridge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PatientResponseDTO(
        Long id,
        String name,
        String email,
        String bloodGroup,
        String gender
) {
}
