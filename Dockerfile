FROM openjdk:17
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Ensure the Maven wrapper can be executed
RUN chmod +x mvnw

# Build the project using Maven
RUN ./mvnw clean package  # Use ./mvnw instead of sh mvnw to ensure it's run correctly

# Specify the command to run the application
CMD ["java", "-jar", "target/expleo-assignment-file.jar"]
