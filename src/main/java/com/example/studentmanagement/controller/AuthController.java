package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.AuthResponse;
import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = httpRequest.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            User user = principal.getUser();

            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), user.getEmail(),
                    user.getRole(), user.getProfileId(), "Login successful"));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, null, null, null, null, "Invalid email or password"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body("Logged out");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }
        CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
        User user = principal.getUser();
        return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), user.getEmail(),
                user.getRole(), user.getProfileId(), "OK"));
    }

    /**
     * Open self-registration always creates a STUDENT account.
     * Staff/Faculty/Admin accounts should be created by a SUPER_ADMIN/ADMIN
     * via the Staff/Faculty management screens (kept simple on purpose).
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }
        newUser.setId(null);
        newUser.setRole("STUDENT");
        newUser.setActive(true);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User saved = userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(saved.getId(), saved.getName(),
                saved.getEmail(), saved.getRole(), saved.getProfileId(), "Registered successfully"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody java.util.Map<String, String> body) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }
        CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
        User user = userRepository.findById(principal.getUser().getId())
                .orElseThrow(() -> new com.example.studentmanagement.exception.ResourceNotFoundException("User not found"));

        String currentPassword = body.get("currentPassword");
        String newPassword = body.get("newPassword");

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is incorrect");
        }
        if (newPassword == null || newPassword.length() < 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New password must be at least 6 characters");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Password updated successfully");
    }
}
