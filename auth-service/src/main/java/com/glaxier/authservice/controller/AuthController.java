package com.glaxier.authservice.controller;

import com.glaxier.authservice.dto.request.LoginRequest;
import com.glaxier.authservice.dto.request.RegistrationRequest;
import com.glaxier.authservice.dto.response.LoginResponse;
import com.glaxier.authservice.service.KeycloakAdminClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private KeycloakAdminClient keycloakAdminClient;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        keycloakAdminClient.register(registrationRequest);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(keycloakAdminClient.login(loginRequest));
    }

    @GetMapping("/protected")
    public ResponseEntity<String> getProtected() {
        return ResponseEntity.ok("User is authenticated.");
    }

    @GetMapping("/restricted-user")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<String> isUser() {
        return ResponseEntity.ok("User authenticated as a user.");
    }

    @GetMapping("/restricted-admin")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> isAdmin() {
        return ResponseEntity.ok("User authenticated as a admin.");
    }
}