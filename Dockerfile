FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/eventmeetup-0.0.1-SNAPSHOT.jar eventmeetup.jar
ENV SPRING_PROFILES_ACTIVE=local
ENTRYPOINT ["java", "-jar","eventmeetup.jar"]