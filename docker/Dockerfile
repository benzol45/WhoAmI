FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/WhoAmI-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]