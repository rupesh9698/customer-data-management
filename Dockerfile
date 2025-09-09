# Use an official Amazon Corretto 21 image for building the application
FROM amazoncorretto:21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the application source code to the container
COPY . .

# Ensure that the Gradle wrapper script has executable permissions
RUN chmod +x ./gradlew

# Build the Micronaut application using Gradle
RUN ./gradlew assemble

# Use a Corretto slim JRE base image to reduce size for runtime
FROM amazoncorretto:21-alpine AS runtime

# Set the working directory
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*-all.jar /app/app.jar

# Expose the application port specified in the application.yml (port 5424)
EXPOSE 5424

# Set environment variables for PostgreSQL connection
ENV DATASOURCE_URL=postgresql://postgresql_ece9_user:dl83vap5lV6aHrBRxwvIAnpXEw1FiGI9@dpg-d300rg2dbo4c73b599kg-a/postgresql_ece9
ENV DATASOURCE_USERNAME=postgresql_ece9_user
ENV DATASOURCE_PASSWORD=dl83vap5lV6aHrBRxwvIAnpXEw1FiGI9
ENV DATASOURCE_DIALECT=POSTGRES

# Define the default command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Health check (optional, adjust based on your endpoint)
HEALTHCHECK --interval=30s --timeout=30s --start-period=30s --retries=3 \
  CMD curl --fail http://localhost:5424/health || exit 1