# Stage 1: Build the application
FROM gradle:8.5.0-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]