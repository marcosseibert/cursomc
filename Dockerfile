FROM gradle:jdk11 as builder

COPY --chown=gradle:gradle . /home/src
WORKDIR /home/src
RUN gradle build

FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7

# Dynatrace
ARG DYNATRACE_NAME
USER root

# Environment variables for Dynatrace
ENV DT_WSAGENT_INSTALLER64_URL "http://10.150.41.156:8081/artifactory/libs-release/dynatrace_wsagent.tar"

# Install the Agent
ADD ${DT_WSAGENT_INSTALLER64_URL} /tmp

RUN tar -C /opt -xf /tmp/`basename ${DT_WSAGENT_INSTALLER64_URL}`
RUN rm -f /tmp/`basename ${DT_WSAGENT_INSTALLER64_URL}`
RUN chmod 755 /opt/dynatrace
ENV DYNATRACE_NAME ${DYNATRACE_NAME}
USER 185

# App
CMD mkdir /app
COPY --from=builder /home/src/build/libs/*.jar /app/app.jar

# Ports exposing
EXPOSE 9080

# Entry
COPY cmds/container/entrypoint.sh /opt/entrypoint.sh
ENTRYPOINT /opt/entrypoint.sh
