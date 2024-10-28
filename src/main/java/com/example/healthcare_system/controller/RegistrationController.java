package com.example.healthcare_system.controller;

import com.example.healthcare_system.models.User;
import com.example.healthcare_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Empty user object to bind the form
        return "user"; // Thymeleaf template name
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user"; // Return back to the form if validation errors exist
        }

        userService.saveUser(user);
        // Save the user or perform further processing
        model.addAttribute("message", "User registered successfully");
        return "success"; // Redirect to a success page
    }
}

