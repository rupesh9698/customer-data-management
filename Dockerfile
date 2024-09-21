# Use an official OpenJDK 21 image as a parent image
FROM eclipse-temurin:21-jdk AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the application source code to the container
COPY . .

# Build the Micronaut application using Gradle/Maven (depending on your build tool)
# Uncomment the one you're using

# For Gradle
RUN ./gradlew assemble

# For Maven
# RUN ./mvnw package

# Use a slim JRE base image to reduce size for runtime
FROM eclipse-temurin:21-jre-slim AS runtime

# Set the working directory
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the application port specified in the application.yml (port 5424)
EXPOSE 5424

# Set environment variables for PostgreSQL connection
ENV DATASOURCE_URL=jdbc:postgresql://dpg-crnd9j68ii6s73ep59fg-a:5432/customer_data_management_cbz7
ENV DATASOURCE_USERNAME=customer_data_management_cbz7_user
ENV DATASOURCE_PASSWORD=RORP8cj3sMAAt2o5TPTgufA7VMz6MMih
ENV DATASOURCE_DIALECT=POSTGRES

# Define the default command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Health check (optional, adjust based on your endpoint)
HEALTHCHECK --interval=30s --timeout=30s --start-period=30s --retries=3 \
  CMD curl --fail http://localhost:5424/health || exit 1
