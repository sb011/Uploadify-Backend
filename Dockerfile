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
# FROM openjdk:17-alpine
# WORKDIR /app
# COPY --from=build /app/target/*.jar /app/app.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","app.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080