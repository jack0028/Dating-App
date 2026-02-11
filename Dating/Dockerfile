FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /opt/app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /opt/app
COPY --from=build /opt/app/target/spring-boot-2-hello-world-1.0.2-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
