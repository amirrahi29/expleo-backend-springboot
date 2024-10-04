FROM openjdk:17
WORKDIR /app

COPY . .

# Ensure the Maven wrapper can be executed
RUN chmod +x mvnw || true  # Just in case the command is not found, it will ignore the error

# Build the project using Maven
RUN sh mvnw clean package

# Specify the command to run the application
CMD ["java", "-jar", "target/expleo-assignment-file.jar"]
