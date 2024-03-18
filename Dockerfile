FROM openjdk:latest
COPY target/FinalProject-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
