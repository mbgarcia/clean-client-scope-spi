package br.com.keycloak.provider;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class CleanupProviderFactory implements RealmResourceProviderFactory {
    @Override
    public RealmResourceProvider create(KeycloakSession keycloakSession) {
        return new CleanupProvider(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {
        System.out.println("***** CleanupProviderFactory: Version 1.0 *****");
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "cleanup";
    }
}
