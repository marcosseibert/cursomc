FROM openjdk:8
ADD target/cursomc-0.0.1-SNAPSHOT.jar cursomc-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","cursomc-0.0.1-SNAPSHOT.jar"]