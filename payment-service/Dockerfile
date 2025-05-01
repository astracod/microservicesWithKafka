FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /app/target/order-service-*.jar /app/order-service.jar
CMD ["java", "-jar", "/app/order-service.jar"]