package com.example.healthcare_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Mapping for the home page
    public String home(Model model) {

        // You can add any attributes you want to send to the view here
        // For example:
        model.addAttribute("totalA", 0); // Replace with actual data
        model.addAttribute("totalD", 0); // Replace with actual data
        model.addAttribute("totalP", 0); // Replace with actual data

        return "home"; // Return the home.html template
    }

}
