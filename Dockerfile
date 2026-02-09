FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY bugfix-ai-service ./bugfix-ai-service
WORKDIR /app/bugfix-ai-service

# 🔥 FIX: make mvnw executable
RUN chmod +x mvnw

# build
RUN ./mvnw clean package -DskipTests

# =============================

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/bugfix-ai-service/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
