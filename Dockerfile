# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src src

# Build the application
RUN chmod +x mvnw.cmd && ./mvnw.cmd clean package -DskipTests

# Expose port
EXPOSE 8088

# Set environment variable for production
ENV SPRING_PROFILES_ACTIVE=production

# Run the application
CMD ["java", "-jar", "target/task-list-app-0.0.1-SNAPSHOT.jar"]
