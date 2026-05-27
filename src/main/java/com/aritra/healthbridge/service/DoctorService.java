package com.aritra.healthbridge.service;

import com.aritra.healthbridge.dto.DoctorRequestDTO;
import com.aritra.healthbridge.dto.DoctorResponseDTO;
import com.aritra.healthbridge.entity.Doctor;
import com.aritra.healthbridge.exception.ResourceNotFoundException;
import com.aritra.healthbridge.mapper.DoctorMapper;
import com.aritra.healthbridge.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorResponseDTO addNewDoctor(DoctorRequestDTO doctorRequestDTO){
        Doctor doctor = doctorMapper.mapToEntity(doctorRequestDTO);
        return doctorMapper.mapToDTo(doctorRepository.save(doctor));
    }
    public DoctorResponseDTO updateDoctorDetails(DoctorRequestDTO doctorRequestDTO,Long id){
        Doctor existingDoctor = getDoctorEntityById(id);
        Doctor updatedDoctor = doctorMapper.mapToEntity(doctorRequestDTO);
        updatedDoctor.setId(existingDoctor.getId());
        return doctorMapper.mapToDTo(doctorRepository.save(updatedDoctor));
    }
    public List<DoctorResponseDTO> getAllDoctors(){

        List<Doctor> doctors = doctorRepository.findAll().stream().toList();
        return doctors
                .stream()
                .map(doctorMapper::mapToDTo)
                .toList();
    }
    public DoctorResponseDTO getDoctorById(Long id){
        Doctor doc = getDoctorEntityById(id);
        return doctorMapper.mapToDTo(doc);
    }
    private Doctor getDoctorEntityById(Long id){
        return doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor not found with id: "+id));
    }
}
