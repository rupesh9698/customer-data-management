# --- Build stage ---
# Use official Gradle 9 with JDK 21
FROM gradle:9.0.0-jdk21 AS build

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Ensure gradlew has correct permissions (fix for Windows checkouts)
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# Build application JAR without using daemon (limits memory)
# RUN ./gradlew --no-daemon -Dorg.gradle.jvmargs="-Xmx512m" clean build
RUN ./gradlew assemble

# --- Runtime stage ---
FROM amazoncorretto:21-alpine AS runtime

# Install curl (needed for healthcheck)
RUN apk add --no-cache curl

WORKDIR /app

# Copy built application (regular JAR, since no Shadow)
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose application port
EXPOSE 5424

# Database configuration (injected at runtime by Render)
ENV DATASOURCE_URL=jdbc:postgresql://dpg-d300rg2dbo4c73b599kg-a:5432/postgresql_ece9
ENV DATASOURCE_USERNAME=postgresql_ece9_user
ENV DATASOURCE_PASSWORD=dl83vap5lV6aHrBRxwvIAnpXEw1FiGI9
ENV DATASOURCE_DIALECT=POSTGRES

# Run the Micronaut app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Health check endpoint
HEALTHCHECK --interval=30s --timeout=30s --start-period=30s --retries=3 \
  CMD curl --fail https://customer-data-management-micronaut.onrender.com/health || exit 1