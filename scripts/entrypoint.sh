#!/bin/sh
#exit immediately if a command exits with a non-zero status
set -e

echo "Running custom initialization..."

/opt/keycloak/bin/run-me.sh

echo "Starting Keycloak..."

exec /opt/keycloak/bin/kc.sh start-dev