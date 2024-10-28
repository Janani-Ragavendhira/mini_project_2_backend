package com.example.healthcare_system.service;

import com.example.healthcare_system.models.Doctors;
import com.example.healthcare_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void saveDoctor(Doctors doctor) {
        doctorRepository.save(doctor); // Save the doctor to the database
    }

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll(); // Retrieve all doctors
    }
}
