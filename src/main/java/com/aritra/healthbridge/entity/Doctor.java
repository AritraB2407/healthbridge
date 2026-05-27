package com.aritra.healthbridge.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String degree;
    private String department;
    private LocalDate yearOfPassing;
    private String specialization;
    private String email;
    private String phone;
    private Double consultationFee;

    @Transient
    public int getExperience() {
        return Period.between(
                yearOfPassing,
                LocalDate.now()
        ).getYears();
    }
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}


