package br.com.keycloak.provider;

import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;

public class CleanupProvider implements RealmResourceProvider {
    private final KeycloakSession keycloakSession;

    public CleanupProvider(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
    }

    @Override
    public Object getResource() {
        return new CleanupResource(keycloakSession);
    }

    @Override
    public void close() {

    }
}
