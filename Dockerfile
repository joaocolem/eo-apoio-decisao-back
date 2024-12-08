FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/events-microservice-0.0.1-SNAPSHOT.jar /app/event-microservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "event-microservice.jar"]
