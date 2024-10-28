package com.example.healthcare_system.service;

import com.example.healthcare_system.models.Patient;
import com.example.healthcare_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public List<Patient> getAllPatients() {
        System.out.println("fetching all patients");
        return patientRepository.findAll();
    }

    @Transactional
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Transactional
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

}
