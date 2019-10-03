FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/cursomc-0.0.1-SNAPSHOT.jar cursomc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","cursomc-0.0.1-SNAPSHOT.jar"]
