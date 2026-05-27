package com.aritra.healthbridge.mapper;

import com.aritra.healthbridge.dto.PatientRequestDTO;
import com.aritra.healthbridge.dto.PatientResponseDTO;
import com.aritra.healthbridge.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
     @Mapping(target = "id", ignore = true)
     Patient mapToEntity(PatientRequestDTO patientRequestDTO);
     PatientResponseDTO mapToDto(Patient patient);
     }
