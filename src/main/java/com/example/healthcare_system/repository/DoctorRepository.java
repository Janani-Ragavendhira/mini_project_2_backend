package com.example.healthcare_system.repository;

import com.example.healthcare_system.models.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Long> {
}
