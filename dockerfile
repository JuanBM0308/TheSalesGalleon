FROM openjdk:24-jdk-bullseye
COPY target/the-sales-galleon-0.0.1.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]
