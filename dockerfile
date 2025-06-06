FROM openjdk:24-jdk-bullseye
COPY target/the-sales-galleon-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]
