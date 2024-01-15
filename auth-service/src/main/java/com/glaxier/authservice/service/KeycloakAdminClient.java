package com.glaxier.authservice.service;

import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;

@Service
public class KeycloakAdminClient {
    private Keycloak keycloak;

//    public void createUser(String username, String password) {
//        // Create a new Keycloak user
//        UserRepresentation user = new UserRepresentation();
//        user.setUsername(username);
//        user.setEnabled(true);
//        user.setCredentials(Arrays.asList(new CredentialRepresentation("password", password, true)));
//
//        keycloak.realm("your-realm").users().create(user);
//    }
}
