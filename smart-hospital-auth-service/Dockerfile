FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/smart-hospital-auth-service-1.4.0.jar app.jar
EXPOSE 8144
ENTRYPOINT ["java", "-jar", "app.jar"]