package com.aritra.healthbridge.dto;

import com.aritra.healthbridge.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.aritra.healthbridge.enums.AppointmentStatus.PENDING;

public record AppointmentRequestDTO(
        @NotNull Long doctorId,
        @NotNull Long patientId,
        String notes,
        @NotNull LocalDateTime appointmentDateTime
) {}
