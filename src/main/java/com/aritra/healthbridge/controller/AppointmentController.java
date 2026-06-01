package com.aritra.healthbridge.controller;

import com.aritra.healthbridge.dto.AppointmentRequestDTO;
import com.aritra.healthbridge.dto.AppointmentResponseDTO;
import com.aritra.healthbridge.dto.PatientRequestDTO;
import com.aritra.healthbridge.dto.PatientResponseDTO;
import com.aritra.healthbridge.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
     public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointmentRequestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO,@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.updateAppointmentById(appointmentRequestDTO,id));
    }
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments(){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id){
          appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }


}
