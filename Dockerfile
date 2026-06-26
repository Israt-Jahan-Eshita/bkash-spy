# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage (tiny image)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Bind to 0.0.0.0 so it's reachable from outside the container
ENV PORT=8000
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar", "--server.address=0.0.0.0"]
