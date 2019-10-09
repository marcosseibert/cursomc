FROM maven:3.6.0-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=build /usr/src/app/target/cursomc-0.0.1-SNAPSHOT.jar cursomc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","cursomc-0.0.1-SNAPSHOT.jar"]
