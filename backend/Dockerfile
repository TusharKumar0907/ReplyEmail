# --- Build Stage ---
    FROM maven:3.9.6-eclipse-temurin-21 AS build

    WORKDIR /app
    
    # Copy only the pom.xml to download dependencies first.
    COPY pom.xml .  
    RUN mvn dependency:go-offline
    
    # Copy the source code and build the application.
    COPY src ./src
    RUN mvn clean package -DskipTests
    
    # --- Runtime Stage ---
    FROM openjdk:21-jdk-slim
    
    WORKDIR /app
    
    # Copy the JAR file from the build stage.
    COPY --from=build /app/target/email-writer-sb-0.0.1-SNAPSHOT.jar .
    
    EXPOSE 8080
    
    ENTRYPOINT ["java", "-jar", "/app/email-writer-sb-0.0.1-SNAPSHOT.jar"]