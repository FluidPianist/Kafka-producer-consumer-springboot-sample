# Use a base image with OpenJDK 17 (JRE)
FROM eclipse-temurin:17

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container at /app
COPY target/FirstApp-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy the configuration file into the container at /app
#COPY secrets /app/secrets

# Expose the port your application will run on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "/app/app.jar"]
