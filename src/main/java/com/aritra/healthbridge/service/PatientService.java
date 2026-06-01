package com.aritra.healthbridge.service;

import com.aritra.healthbridge.dto.PatientRequestDTO;
import com.aritra.healthbridge.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {

    PatientResponseDTO createPatient(PatientRequestDTO patient);
    List<PatientResponseDTO> getAllPatients();
    PatientResponseDTO getPatientById(Long id);
    PatientResponseDTO updatePatient(Long id, PatientRequestDTO updatedPatient);
    void deletePatient(Long id);
}
