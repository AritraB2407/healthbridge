package com.aritra.healthbridge.dto;

import com.aritra.healthbridge.enums.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        String doctorName,
        String patientName,
        LocalDateTime appointmentDateTime,
        String department,
        String notes,
        AppointmentStatus status,
        BigDecimal consultationFee
) {}
