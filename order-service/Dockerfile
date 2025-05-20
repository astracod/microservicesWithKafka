FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests  # Собираем проект order-service

FROM openjdk:17-alpine
COPY --from=build /app/target/order-service-*.jar /app/order-service.jar
CMD ["java", "-jar", "/app/order-service.jar"]
