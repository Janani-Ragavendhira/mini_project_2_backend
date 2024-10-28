package com.example.healthcare_system.service;

import com.example.healthcare_system.models.User;
import com.example.healthcare_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    // Add BCryptPasswordEncoder for hashing passwords
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user); // Save the user to the database
    }

    // You could also add a method to find user by email if needed for login functionality
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean validateUser(String email, String password) {
        // Find the user by email
        User user = findByEmail(email);

        // Check if the user exists and the password matches
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword()); // Check password
        }
        return false; // User not found or password doesn't match
    }
}
