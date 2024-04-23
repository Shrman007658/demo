# Use the official OpenJDK 17 image as base
FROM openjdk:17-jdk-alpine

# Copy the compiled Java application JAR file into the container
COPY ./target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1.jar

WORKDIR /app

# Define the command to run your Java application
CMD ["java", "-jar", "demo-0.0.1.jar"]

