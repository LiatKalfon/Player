# Use the official Maven image as the base image
FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code to the working directory
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight Java image as the base image for the runtime environment
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file from the build stage
COPY --from=build /app/target/player-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port that the application listens on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]docke
