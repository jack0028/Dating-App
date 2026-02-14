# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /opt/app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage (Tomcat)
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps

# Remove default ROOT app
RUN rm -rf ROOT

# Copy WAR into Tomcat as ROOT.war
COPY --from=build /opt/app/target/dating-app-0.0.1-SNAPSHOT.war ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
