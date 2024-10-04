# Use the official OpenJDK image as a base
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml to download dependencies
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copy the entire project
COPY . .

# Build the project
RUN ./mvnw clean package

# Copy the JAR file to the Docker image
COPY target/expleo-assignment-file.jar expleo-assignment-file.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the entry point for the Docker container
ENTRYPOINT ["java", "-jar", "/expleo-assignment-file.jar"]
