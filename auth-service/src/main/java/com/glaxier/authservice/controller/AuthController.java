package com.glaxier.authservice.controller;

import com.glaxier.authservice.service.KeycloakAdminClient;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/auth")
                .realm("e_commerce")
                .clientId("e_commerce-client")
                .clientSecret("fb2JvrrFnLgV56Txu0Qx43XQJynbbmHS")
                .username(username)
                .password(password)
                .grantType("password")
                .build();

        // Obtain the token
        var response = keycloak.tokenManager().getAccessToken();
        String accessToken = response.getToken();

        // Return the token or handle as needed
        return ResponseEntity.ok(accessToken);
    }
}