package com.example.healthcare_system.controller.ui;

import com.example.healthcare_system.models.Patient;
import com.example.healthcare_system.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/patients")
public class PatientUiController {

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/registration";
    }

}
