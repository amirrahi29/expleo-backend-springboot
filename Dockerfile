FROM openjdk:17
ADD target/expleo-assignment-file.jar expleo-assignment-file.jar
ENTRYPOINT ["java","-jar","/expleo-assignment-file.jar"]