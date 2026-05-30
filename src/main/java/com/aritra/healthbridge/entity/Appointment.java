package com.aritra.healthbridge.entity;

import com.aritra.healthbridge.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name= "appointments")
@EqualsAndHashCode(callSuper = true)
@Data
public class Appointment extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    private String notes;

}
