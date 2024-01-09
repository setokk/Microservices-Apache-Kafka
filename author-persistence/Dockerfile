# Build stage
FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:21-jdk-bullseye
COPY --from=build /home/app/target/*.jar app.jar

EXPOSE 8081

COPY ./wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "db:5433", "--", "java", "-jar", "app.jar"]