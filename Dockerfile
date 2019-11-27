FROM openjdk:11-jdk
COPY build/libs/user-management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]