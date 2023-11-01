FROM openjdk:18
 
WORKDIR /app
 
COPY ./target/Backend-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080
 
CMD ["java", "-jar", "Backend-0.0.1-SNAPSHOT.jar"]