package com.hmsbe.controller;

import com.hmsbe.entity.Doctor;
import com.hmsbe.exception.ResourceNotFoundException;
import com.hmsbe.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/doctor")
public class DocterController {

    @Autowired
    private DoctorRepository doctorRepo;

    @GetMapping("/")
    public List<Doctor> getDoctors() {
        return doctorRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getUser(@PathVariable Long id) {

        Optional<Doctor> doctor = doctorRepo.findById(id);

        if(doctor.isEmpty()) {
            throw new ResourceNotFoundException("Doctor not found for given ID " + id);
        }

        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Doctor doctorParam) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            doctorRepo.save(doctorParam);
            response.put("status", "success");
            response.put("message", "Doctor Created");
        } catch(Exception e) {
            response.put("message", "Unable to login");
            return response;
        }

        return response;
    }

    @PutMapping
    public Map<String, String> update(@RequestBody Doctor doctorParam) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            Optional<Doctor> existingDoctor = doctorRepo.findById(doctorParam.getId());

            if( existingDoctor.isEmpty() ) {
                response.put("message", "Invalid Doctor");
                return response;
            }

            doctorRepo.save(doctorParam);
            response.put("status", "success");
            response.put("message", "Doctor Updated");
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

            Optional<Doctor> existingDoctor = doctorRepo.findById(id);

            if( existingDoctor.isEmpty() ) {
                response.put("message", "Invalid Doctor");
                return response;
            }

            doctorRepo.delete(existingDoctor.get());
            response.put("status", "success");
            response.put("message", "Doctor Deleted");
        } catch (Exception e) {
            response.put("message", "Unable to delete");
            return response;
        }

        return response;
    }

}
