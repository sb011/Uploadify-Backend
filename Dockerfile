#
# Build stage
#
# FROM maven:3.8.3-openjdk-17 AS build
# WORKDIR /app
# COPY . /app/
# RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-alpine
WORKDIR /app
COPY /app/target/Backend-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]