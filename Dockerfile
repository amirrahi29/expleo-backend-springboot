FROM openjdk:17
ADD target/expleo-assignment-file.jar expleo-assignment-file.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/expleo-assignment-file.jar"]
