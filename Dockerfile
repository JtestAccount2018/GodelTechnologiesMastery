FROM openjdk:8-jdk-alpine
MAINTAINER Uladzimir Pratsevich
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

