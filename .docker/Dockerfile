FROM openjdk:11-jre-slim

ARG JAR_FILE
WORKDIR /app

COPY target/${JAR_FILE} /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "/app/api.jar"]