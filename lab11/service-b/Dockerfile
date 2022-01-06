FROM icr.io/appcafe/open-liberty:full-java11-openj9-ubi

USER root

COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
COPY --chown=1001:0 target/*.war /config/apps/
COPY --chown=1001:0 jdbc/*.jar /config/jdbc/

USER 1001
