package com.aritra.healthbridge.controller;

import com.aritra.healthbridge.dto.PatientRequestDTO;
import com.aritra.healthbridge.dto.PatientResponseDTO;
import com.aritra.healthbridge.entity.Patient;
import com.aritra.healthbridge.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDTO>>getAllPatients(){
    return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patient){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.createPatient(patient));
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Valid @RequestBody PatientRequestDTO patient, @PathVariable Long id){
        return ResponseEntity.ok(patientService.updatePatient(id ,patient));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id ){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
