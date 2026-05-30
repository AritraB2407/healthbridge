package com.aritra.healthbridge.service;

import com.aritra.healthbridge.dto.DoctorRequestDTO;
import com.aritra.healthbridge.dto.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {
     DoctorResponseDTO addNewDoctor(DoctorRequestDTO doctorRequestDTO);
     DoctorResponseDTO updateDoctorDetails(DoctorRequestDTO doctorRequestDTO,Long id);
     List<DoctorResponseDTO> getAllDoctors();
     DoctorResponseDTO getDoctorById(Long id);

}
