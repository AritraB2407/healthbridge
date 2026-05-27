package com.aritra.healthbridge.repository;

import com.aritra.healthbridge.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
