package com.aritra.healthbridge.mapper;

import com.aritra.healthbridge.dto.DoctorRequestDTO;
import com.aritra.healthbridge.dto.DoctorResponseDTO;
import com.aritra.healthbridge.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "id", ignore = true)
    Doctor mapToEntity(DoctorRequestDTO doctorRequestDTO);
    DoctorResponseDTO mapToDTo(Doctor doctor);
}
