package com.aritra.healthbridge.mapper;

import com.aritra.healthbridge.dto.AppointmentResponseDTO;
import com.aritra.healthbridge.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "doctorName", source = "doctor.name")
    @Mapping(target = "patientName", source = "patient.name")
    @Mapping(target = "department", source = "doctor.department")
    @Mapping(target = "consultationFee", source = "doctor.consultationFee")

    AppointmentResponseDTO mapToDto(Appointment appointment);
}
