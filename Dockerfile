# Use OpenJDK 8 as base image
FROM openjdk:8-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src src

# Build the application
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Set environment variable for production
ENV SPRING_PROFILES_ACTIVE=production

# Run the application
CMD ["java", "-jar", "target/task-list-app-0.0.1-SNAPSHOT.jar"]
