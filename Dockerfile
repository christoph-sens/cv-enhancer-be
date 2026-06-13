FROM gradle:9.5.1-jdk25 AS build
WORKDIR /app
COPY . /app/.
RUN ./gradlew :bootstrap:bootJar --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/bootstrap/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]