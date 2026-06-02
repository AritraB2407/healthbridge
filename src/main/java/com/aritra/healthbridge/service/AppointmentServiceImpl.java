package com.aritra.healthbridge.service;

import com.aritra.healthbridge.dto.AppointmentRequestDTO;
import com.aritra.healthbridge.dto.AppointmentResponseDTO;
import com.aritra.healthbridge.entity.Appointment;
import com.aritra.healthbridge.enums.AppointmentStatus;
import com.aritra.healthbridge.event.AppointmentBookedEvent;
import com.aritra.healthbridge.exception.ResourceNotFoundException;
import com.aritra.healthbridge.kafka.AppointmentEventProducer;
import com.aritra.healthbridge.mapper.AppointmentMapper;
import com.aritra.healthbridge.repository.AppointmentRepository;
import com.aritra.healthbridge.repository.DoctorRepository;
import com.aritra.healthbridge.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentEventProducer appointmentEventProducer;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctorRepository.findById(appointmentRequestDTO.doctorId())
                .orElseThrow(()-> new ResourceNotFoundException("Doctor not found for id "+appointmentRequestDTO.doctorId())));
        appointment.setPatient(patientRepository.findById(appointmentRequestDTO.patientId())
                .orElseThrow(()-> new ResourceNotFoundException("Patient not found for id "+appointmentRequestDTO.patientId())));
        appointment.setAppointmentDateTime(appointmentRequestDTO.appointmentDateTime());
        appointment.setStatus(AppointmentStatus.PENDING);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        AppointmentBookedEvent event = new AppointmentBookedEvent(savedAppointment.getId(),
                savedAppointment.getPatient().getName(),savedAppointment.getDoctor().getName(),savedAppointment.getAppointmentDateTime());
        appointmentEventProducer.publishAppointmentBooked(event);

        return appointmentMapper.mapToDto(savedAppointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                        .map(appointmentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        return appointmentMapper.mapToDto(getAppointmentByID(id));
    }

    @Override
    public AppointmentResponseDTO updateAppointmentById(AppointmentRequestDTO appointmentRequestDTO, Long id) {

        Appointment existingAppointment = getAppointmentByID(id);
        existingAppointment.setAppointmentDateTime(appointmentRequestDTO.appointmentDateTime());
        return appointmentMapper.mapToDto(appointmentRepository.save(existingAppointment));
    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment existingAppointment = getAppointmentByID(id);
        existingAppointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(existingAppointment);
    }
    private Appointment getAppointmentByID(Long id){
        return appointmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Appointment not found for id : "+id));
    }

}
