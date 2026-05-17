# Multi-stage Dockerfile for building a Spring Boot app
# Build stage
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml mvnw .mvn/ ./
COPY . ./
# Use the Maven wrapper if present, otherwise fall back to mvn
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -DskipTests package -DskipITs

# Run stage
FROM eclipse-temurin:21-jre
ARG JAR_FILE=target/*.jar
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
