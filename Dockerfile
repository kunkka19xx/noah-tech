FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/noah-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080