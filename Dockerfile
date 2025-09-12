# --- Build stage ---
FROM gradle:9.0.0-jdk21 AS build

WORKDIR /app

# Copy everything at once
COPY . .

# Fix permissions for gradlew
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# Build application with memory constraints for Render's environment
RUN ./gradlew --no-daemon \
    -Dorg.gradle.jvmargs="-Xmx512m -XX:MaxMetaspaceSize=128m -XX:+UseContainerSupport" \
    -Dorg.gradle.workers.max=2 \
    clean shadowJar -x test

# --- Runtime stage ---
FROM amazoncorretto:21-alpine AS runtime

# Install curl for healthcheck
RUN apk add --no-cache curl

WORKDIR /app

# Copy the built JAR
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Create non-root user
RUN addgroup -g 1001 appgroup && adduser -u 1001 -G appgroup -s /bin/sh -D appuser
RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8080

# Environment variables
ENV MICRONAUT_ENVIRONMENTS=production
ENV JAVA_OPTS="-Xmx256m -XX:+UseContainerSupport"

# Database config
ENV DATASOURCE_URL=jdbc:postgresql://ep-quiet-meadow-a14vpajc.ap-southeast-1.pg.koyeb.app/Customer_Data_Management
ENV DATASOURCE_USERNAME=postgresql_ece9_user
ENV DATASOURCE_PASSWORD=dl83vap5lV6aHrBRxwvIAnpXEw1FiGI9
ENV DATASOURCE_DIALECT=POSTGRES

# Run the app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

# Health check using localhost
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl --fail http://localhost:8080/health || exit 1