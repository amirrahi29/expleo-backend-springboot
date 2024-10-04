# Use the official OpenJDK image as a base
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml to download dependencies
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Ensure that the Maven wrapper has executable permissions
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the entire project
COPY . .

# Build the project and create the JAR file
RUN ./mvnw clean package

# Verify the JAR file is generated and then copy it
RUN ls target/  # Verify the contents of the target directory
COPY target/*.jar expleo-assignment-file.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the entry point for the Docker container
ENTRYPOINT ["java", "-jar", "/expleo-assignment-file.jar"]
