FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests  # Собираем проект

FROM openjdk:17-alpine
COPY --from=build /app/target/payment-service-*.jar /app/payment-service.jar
CMD ["java", "-jar", "/app/payment-service.jar"]  # Изменили имя JAR-файла