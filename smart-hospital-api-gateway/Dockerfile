FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/smart-hospital-api-gateway-1.0.jar app.jar
EXPOSE 8020
ENTRYPOINT ["java", "-jar", "app.jar"]