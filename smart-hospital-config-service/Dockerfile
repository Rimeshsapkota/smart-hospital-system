FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/smart-hospital-config-service-1.0.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]