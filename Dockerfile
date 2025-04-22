# Use OpenJDK 17
FROM openjdk:17

# Create working directory in container
WORKDIR /app

# Copy the Spring Boot JAR file to the container
COPY target/Redis-Example-0.0.1-SNAPSHOT.jar /app/

# Set the entry point for the container
CMD ["java", "-jar", "Redis-Example-0.0.1-SNAPSHOT.jar"]
