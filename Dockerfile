FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/grupos-0.0.1-SNAPSHOT.jar /app/grupo.jar


EXPOSE 8081

ENTRYPOINT ["java", "-jar", "grupo.jar"]
