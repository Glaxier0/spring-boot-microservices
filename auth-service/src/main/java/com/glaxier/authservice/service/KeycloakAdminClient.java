package com.glaxier.authservice.service;

import com.glaxier.authservice.dto.request.LoginRequest;
import com.glaxier.authservice.dto.request.RegistrationRequest;
import com.glaxier.authservice.dto.response.LoginResponse;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class KeycloakAdminClient {
    @Value("${keycloak.server.url}")
    private String serverUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.client.id}")
    private String clientId;
    @Value("${keycloak.client.secret}")
    private String clientSecret;
    @Value("${keycloak.admin.realm}")
    private String adminRealm;
    @Value("${keycloak.admin.client.id}")
    private String adminClientId;
    @Value("${keycloak.admin.client.secret}")
    private String adminClientSecret;
    @Value("${keycloak.admin.user}")
    private String admin;
    @Value("${keycloak.admin.password}")
    private String password;

    public void register(RegistrationRequest registrationRequest) {
        var keycloak = adminLogin();


        UserRepresentation user = new UserRepresentation();
        user.setUsername(registrationRequest.getUsername());
        user.setEnabled(true);

        var credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(registrationRequest.getPassword());
        credentialRepresentation.setTemporary(false);

        user.setCredentials(Collections.singletonList(credentialRepresentation));
        // add exception handler and save user to postgres
        var temp = keycloak.realm(realm).users().create(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();

        var response = keycloak.tokenManager().getAccessToken();
        var loginResponse = new LoginResponse(response.getToken(), response.getRefreshToken());

        return loginResponse;
    }

    private Keycloak adminLogin() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(adminRealm)
                .clientId(adminClientId)
                .clientSecret(adminClientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .username(admin)
                .password(password)
                .build();
    }
}
