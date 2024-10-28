package com.example.healthcare_system.service;

import com.example.healthcare_system.models.User;
import com.example.healthcare_system.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository; // Injecting the login repository

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean validateUser(String email, String password) {
        // Find user by email using LoginRepository
        User user = loginRepository.findByEmail(email);

        // Check if user exists and if the password matches
        if (user != null) {
            // Use BCrypt to compare hashed passwords (for security)
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
