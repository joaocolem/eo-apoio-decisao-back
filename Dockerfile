FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

CMD ["java", "-jar", "event-microservice.jar"]