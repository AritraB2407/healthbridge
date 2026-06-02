package com.aritra.healthbridge.event;

import java.time.LocalDateTime;

public record AppointmentBookedEvent(
        Long appointmentId,
        String patientName,
        String doctorName,
        LocalDateTime appointmentDateTime
) {
}