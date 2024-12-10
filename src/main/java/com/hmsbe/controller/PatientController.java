package com.hmsbe.controller;

import com.hmsbe.entity.Doctor;
import com.hmsbe.entity.Patient;
import com.hmsbe.exception.ResourceNotFoundException;
import com.hmsbe.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepo;

    @GetMapping("/")
    public List<Patient> getPatients() {
        return patientRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getUser(@PathVariable Long id) {

        Optional<Patient> patient = patientRepo.findById(id);

        if(patient.isEmpty()) {
            throw new ResourceNotFoundException("Patient not found for given ID " + id);
        }

        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Patient patientParam) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            patientRepo.save(patientParam);
            response.put("status", "success");
            response.put("message", "Patient Created");
        } catch(Exception e) {
            response.put("message", "Unable to login");
            return response;
        }

        return response;
    }

    @PutMapping
    public Map<String, String> update(@RequestBody Patient patientParam) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            Optional<Patient> existingDoctor = patientRepo.findById(patientParam.getId());

            if( existingDoctor.isEmpty() ) {
                response.put("message", "Invalid Patient");
                return response;
            }

            patientRepo.save(patientParam);
            response.put("status", "success");
            response.put("message", "Patient Updated");
        } catch(Exception e) {
            response.put("message", "Unable to update");
            return response;
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id)  {
        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            if( id < 1 ) {
                response.put("message", "Invalid Request");
                return response;
            }

            Optional<Patient> existingDoctor = patientRepo.findById(id);

            if( existingDoctor.isEmpty() ) {
                response.put("message", "Invalid Patient");
                return response;
            }

            patientRepo.delete(existingDoctor.get());
            response.put("status", "success");
            response.put("message", "Patient Deleted");
        } catch (Exception e) {
            response.put("message", "Unable to delete");
            return response;
        }

        return response;
    }

}
