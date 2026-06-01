package com.aritra.healthbridge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DoctorResponseDTO(
        Long id,
        String name,
        String degree,
        String department,
        int experience,
        String specialization,
        BigDecimal consultationFee
) {

}
