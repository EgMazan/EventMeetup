FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/eventmeetup-0.0.1-SNAPSHOT.jar eventmeetup.jar
ENTRYPOINT ["java", "-jar","eventmeetup.jar"]