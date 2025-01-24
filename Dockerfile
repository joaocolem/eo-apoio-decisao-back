FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY . .

CMD ["java", "-jar", "event-microservice.jar"]