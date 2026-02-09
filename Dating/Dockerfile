# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/dating-app-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
