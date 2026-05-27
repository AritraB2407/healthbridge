package com.aritra.healthbridge.controller;

import com.aritra.healthbridge.dto.DoctorRequestDTO;
import com.aritra.healthbridge.dto.DoctorResponseDTO;
import com.aritra.healthbridge.repository.DoctorRepository;
import com.aritra.healthbridge.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<DoctorResponseDTO> addNewDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addNewDoctor(doctorRequestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> addNewDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO, @PathVariable Long id){
        return ResponseEntity.ok(doctorService.updateDoctorDetails(doctorRequestDTO,id));
    }
    @GetMapping()
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
    @GetMapping("/{id}")

    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
}
