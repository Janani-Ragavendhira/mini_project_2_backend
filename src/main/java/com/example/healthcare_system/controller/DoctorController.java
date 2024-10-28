package com.example.healthcare_system.controller;

import java.util.List;
import com.example.healthcare_system.models.Doctors;
import com.example.healthcare_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Show form to add a doctor
    @GetMapping("/add-doctor")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctors()); // Ensure attribute name matches model
        return "addDoctors"; // Ensure the view name is correct
    }

    // Handle form submission to add a doctor
    @PostMapping("/add-doctor")
    public String addDoctor(@ModelAttribute Doctors doctor) {
        doctorService.saveDoctor(doctor); // Use the method parameter 'doctor' instead of 'Doctors'
        return "redirect:/doctors"; // Redirect to the list of doctors after adding
    }

    // List all doctors
    @GetMapping("/doctors")
    public String listDoctors(Model model) {
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors); // This should be 'doctors' to match the view
        return "doctors"; // Ensure the view name is correct
    }
}
