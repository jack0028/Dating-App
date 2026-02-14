# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /opt/app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jdk
WORKDIR /opt/app
COPY --from=build /opt/app/target/dating-app-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
