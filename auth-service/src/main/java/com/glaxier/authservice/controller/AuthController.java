package com.glaxier.authservice.controller;

import com.glaxier.authservice.dto.request.LoginRequest;
import com.glaxier.authservice.dto.response.LoginResponse;
import com.glaxier.authservice.service.KeycloakAdminClient;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private KeycloakAdminClient keycloakAdminClient;
//
//    @PostMapping
//    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
//        // Use Keycloak Admin Client to create a new user
//        keycloakAdminClient.createUser(registrationRequest.getUsername(), registrationRequest.getPassword());
//
//        return ResponseEntity.ok("User registered successfully");
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("e_commerce")
                .clientId("e_commerce-client")
                .clientSecret("QrOgjWXAqrFnVe7b5kIBYECQ3raOxdjf")
                .grantType(OAuth2Constants.PASSWORD)
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();

        var response = keycloak.tokenManager().getAccessToken();
        var loginResponse = new LoginResponse(loginRequest.getUsername(), response.getToken(), response.getRefreshToken());

        return ResponseEntity.ok(loginResponse);
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