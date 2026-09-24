package com.example.studentmanagement.config;

import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Creates the four demo accounts (documented in README.md) on first startup
 * so the app is usable immediately, without requiring the sample data JSON
 * to be imported first. Safe to run every startup - it only creates users
 * that don't already exist.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createIfMissing("Super Admin", "admin@example.com", "Admin@123", "SUPER_ADMIN");
        createIfMissing("Demo Staff", "staff@example.com", "Staff@123", "STAFF");
        createIfMissing("Demo Faculty", "faculty@example.com", "Faculty@123", "FACULTY");
        createIfMissing("Demo Student", "student@example.com", "Student@123", "STUDENT");
    }

    private void createIfMissing(String name, String email, String rawPassword, String role) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            user.setActive(true);
            userRepository.save(user);
            System.out.println("Created demo user: " + email + " / role: " + role);
        }
    }
}
