package com.aritra.healthbridge.service;

import com.aritra.healthbridge.dto.AppointmentRequestDTO;
import com.aritra.healthbridge.dto.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO);
    List<AppointmentResponseDTO> getAllAppointments();
    AppointmentResponseDTO getAppointmentById(Long id);
    AppointmentResponseDTO updateAppointmentById(AppointmentRequestDTO appointmentRequestDTO,Long id);
    void cancelAppointment(Long id);
}
