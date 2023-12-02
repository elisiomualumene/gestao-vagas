# Build Stage
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests -Dmaven.test.skip=true -Dmaven.build.dest=/app/target


# Final Stage
FROM openjdk:17
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/gestao-vagas-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
# CMD ["tail", "-f", "/dev/null"]