# Use the official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Use official OpenJDK 17 to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]