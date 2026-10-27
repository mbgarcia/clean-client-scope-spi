package br.com.keycloak.provider;

import org.keycloak.models.*;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CleanupResource {
    private final KeycloakSession keycloakSession;

    public CleanupResource(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
    }

    @POST
    @Path("clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response clean() {
        RealmModel realmModel = keycloakSession.getContext().getRealm();

        Pattern pattern = Pattern.compile("^(consent|recurring-consent|enrollment):urn:.*");

        int removedClientScopeCount = 0;

        List<String> ids = realmModel.getClientScopesStream()
                .filter(scope -> pattern.matcher(scope.getName()).matches())
                .map(ClientScopeModel::getId)
                .collect(Collectors.toList());

        for (String id: ids) {
            boolean removed = keycloakSession.clientScopes().removeClientScope(realmModel, id);
            System.out.println("Client scope removed: ".concat("'").concat(id).concat("'").concat(" with success: ").concat(String.valueOf(removed)));
            removedClientScopeCount++;
        }

        return Response.ok("{\"removedClientScopeCount\": ".concat(String.valueOf(removedClientScopeCount)).concat("}")).build();
    }
}
