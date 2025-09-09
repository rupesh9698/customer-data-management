# --- Build stage ---
# Use official Gradle 9 with JDK 21
FROM gradle:9.0.0-jdk21 AS build

# Set working directory
WORKDIR /app

# Copy gradle wrapper and build files first (for better caching)
COPY gradle/ gradle/
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY micronaut-cli.yml ./

# Ensure gradlew has correct permissions
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# Download dependencies (this layer will be cached)
RUN ./gradlew --no-daemon dependencies

# Copy source code
COPY src/ src/

# Build application with memory constraints for Render's environment
RUN ./gradlew --no-daemon \
    -Dorg.gradle.jvmargs="-Xmx768m -XX:MaxMetaspaceSize=256m -XX:+UseContainerSupport" \
    -Dorg.gradle.workers.max=2 \
    clean build -x test

# --- Runtime stage ---
FROM amazoncorretto:21-alpine AS runtime

# Install curl for healthcheck
RUN apk add --no-cache curl

WORKDIR /app

# Copy built JAR
COPY --from=build /app/build/libs/*-all.jar /app/app.jar

# Create non-root user for security
RUN addgroup -g 1001 appgroup && adduser -u 1001 -G appgroup -s /bin/sh -D appuser
RUN chown -R appuser:appgroup /app
USER appuser

# Expose port
EXPOSE 5424

# Environment variables (consider moving sensitive data to Render environment variables)
ENV MICRONAUT_ENVIRONMENTS=production
ENV JAVA_OPTS="-Xmx256m -XX:+UseContainerSupport -XX:+UseG1GC"

# Run application with optimized JVM settings
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl --fail http://localhost:5424/health || exit 1