FROM openjdk:11-jre-slim
FROM maven:3.8.6-openjdk-11-slim AS build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

EXPOSE 8080

CMD ["java", "-jar", "/app/target/tech-challenge-group2-soat1-0.0.1-SNAPSHOT.jar"]