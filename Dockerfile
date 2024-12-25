# Use an official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the .jar file into the container at /app
COPY target/social-project-0.0.1-SNAPSHOT.jar /app/social-project.jar

# Make the application accessible on port 8080
EXPOSE 8080

# Run the .jar file when the container starts
ENTRYPOINT ["java", "-jar", "/app/social-project.jar"]
