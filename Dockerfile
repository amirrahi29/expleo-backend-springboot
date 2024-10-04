FROM openjdk:17
COPY --from=build /usr/src/app/target/projectName-0.0.1-SNAPSHOT.jar expleo-assignment-file.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","expleo-assignment-file.jar"]