# First stage: Build the application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and the Maven wrapper first to cache dependencies
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy the rest of the application files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Second stage: Create the final image
FROM openjdk:17
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/expleo-assignment-file.jar .

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "expleo-assignment-file.jar"]
