package com.aritra.healthbridge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends BaseEntity{

    private String name;
    private String degree;
    private String department;
    private LocalDate yearOfPassing;
    private String specialization;
    private String email;
    private String phone;
    private BigDecimal consultationFee;

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


