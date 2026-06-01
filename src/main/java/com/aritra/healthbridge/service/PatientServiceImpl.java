package com.aritra.healthbridge.service;

import com.aritra.healthbridge.dto.PatientRequestDTO;
import com.aritra.healthbridge.dto.PatientResponseDTO;
import com.aritra.healthbridge.entity.Patient;
import com.aritra.healthbridge.exception.ResourceNotFoundException;
import com.aritra.healthbridge.mapper.PatientMapper;
import com.aritra.healthbridge.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientResponseDTO createPatient(PatientRequestDTO patient){
        Patient patientEntity = patientMapper.mapToEntity(patient);
        return patientMapper.mapToDto(patientRepository.save(patientEntity));
    }
    public List<PatientResponseDTO> getAllPatients(){
        return patientRepository.findAll().stream().map(patientMapper::mapToDto).toList();
    }
    public PatientResponseDTO getPatientById(Long id){
        Patient patient = getPatientByIdFromDB(id);
        return patientMapper.mapToDto(patient);
    }
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO updatedPatient){
        Patient existingpatient = getPatientByIdFromDB(id);
        Patient updated = patientMapper.mapToEntity(updatedPatient);
        updated.setId(existingpatient.getId());

        return patientMapper.mapToDto(patientRepository.save(updated));
    }
    public void deletePatient(Long id){
        Patient toBeRemovedPatient = getPatientByIdFromDB(id);
        patientRepository.delete(toBeRemovedPatient);
    }
    private Patient getPatientByIdFromDB(Long id){
        return patientRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Patient not found with id : "+id));
    }
}
