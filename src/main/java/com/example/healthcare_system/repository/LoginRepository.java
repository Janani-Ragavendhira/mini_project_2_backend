package com.example.healthcare_system.repository;

import com.example.healthcare_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

    // Find user by email for login purposes
    User findByEmail(String email);
}
