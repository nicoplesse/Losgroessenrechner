# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Nur pom.xml kopieren → Dependency Cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Quellcode kopieren & bauen
COPY src ./src
RUN mvn package


# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Expliziter Jar-Name empfohlen
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]
