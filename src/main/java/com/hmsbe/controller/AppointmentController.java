package com.hmsbe.controller;

import com.hmsbe.dto.AppointmentDto;
import com.hmsbe.entity.Appointment;
import com.hmsbe.entity.Doctor;
import com.hmsbe.entity.Patient;
import com.hmsbe.model.AppointmentForm;
import com.hmsbe.repository.AppointmentRepository;
import com.hmsbe.repository.DoctorRepository;
import com.hmsbe.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public List<AppointmentDto> getAppointments() {
        List<Object[]> records = appointmentRepo.findAppointments();

        List<AppointmentDto> appointments = new ArrayList<>();
        Long id = null;
        String doctorName = null, patientName = null, patientMobile = null, appointmentReason = null;
        Date appointmentAt = null;
        for(Object[] record : records) {
            id = (Long) record[0];
            doctorName = (String) record[1];
            patientName = (String) record[2];
            patientMobile = (String) record[3];
            appointmentAt = (Date) record[4];
            appointmentReason = (String) record[5];

            appointments.add(new AppointmentDto(id, doctorName, patientName, patientMobile, appointmentAt, appointmentReason));
        }

        return appointments;
    }

    @GetMapping("/options")
    public AppointmentForm getOptions() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<Patient> patients = patientRepository.findAll();
        AppointmentForm appointmentForm = new AppointmentForm();
        appointmentForm.setDoctors(doctors);
        appointmentForm.setPatients(patients);
        return appointmentForm;
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Appointment appointmentParam) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            appointmentRepo.save(appointmentParam);
            response.put("status", "success");
            response.put("message", "Appointment Created");
        } catch(Exception e) {
            response.put("message", "Unable to create appointment");
            return response;
        }

        return response;
    }

    @PutMapping
    public Map<String, String> update(@RequestBody Appointment appointmentParam) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "fail");

        try {
            Optional<Appointment> existingDoctor = appointmentRepo.findById(appointmentParam.getId());

            if( existingDoctor.isEmpty() ) {
                response.put("message", "Invalid Appointment");
                return response;
            }

            appointmentRepo.save(appointmentParam);
            response.put("status", "success");
            response.put("message", "Appointment Updated");
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

            Optional<Appointment> existingDoctor = appointmentRepo.findById(id);

            if( existingDoctor.isEmpty() ) {
                response.put("message", "Invalid Appointment");
                return response;
            }

            appointmentRepo.delete(existingDoctor.get());
            response.put("status", "success");
            response.put("message", "Appointment Deleted");
        } catch (Exception e) {
            response.put("message", "Unable to delete");
            return response;
        }

        return response;
    }

}
