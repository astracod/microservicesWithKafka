FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests  # Собираем проект payment-service

FROM openjdk:17-alpine
COPY --from=build /app/target/payment-service-*.jar /app/payment-service.jar
CMD ["java", "-jar", "/app/payment-service.jar"]