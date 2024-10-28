package com.example.healthcare_system.controller;

import com.example.healthcare_system.service.LoginService;
import com.example.healthcare_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService; // Inject UserService to validate users


    @GetMapping("/login") // Handles GET requests to show the login page
    public String showLoginPage(Model model) {
        return "login"; // Return the login.html template
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        if (userService.validateUser(email, password)) {
            return "redirect:/"; // Redirect to a success page
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // Return to login page
        }
    }
}
