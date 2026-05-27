package com.aritra.healthbridge.dto;

import java.time.LocalDate;

public record DoctorResponseDTO(
        Long id,
        String name,
        String degree,
        String department,
        int experience,
        String specialization,
        Double consultationFee
) {

}
