FROM quay.io/keycloak/keycloak:21.1.2

WORKDIR /opt/keycloak

COPY --chown=keycloak:keycloak --chmod=644 ./providers/ /opt/keycloak/providers/

# For Keycloak 21, if you add custom providers, you normally need to run the build command to generate the necessary files.
# However, since we are using a custom entrypoint script, we can skip this step and let the script handle it.
RUN /opt/keycloak/bin/kc.sh build

COPY --chown=keycloak:keycloak ./scripts/entrypoint.sh /opt/keycloak/entrypoint.sh

COPY --chown=keycloak:keycloak ./scripts/run-me.sh /opt/keycloak/bin/run-me.sh

USER root

RUN chmod +x /opt/keycloak/bin/run-me.sh

RUN chmod +x /opt/keycloak/entrypoint.sh

ENTRYPOINT [ "/opt/keycloak/entrypoint.sh" ]