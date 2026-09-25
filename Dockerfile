FROM gradle:9.7.1-jdk25 AS build
WORKDIR /app
COPY . /app/.
RUN ./gradlew :bootstrap:bootJar --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
RUN groupadd --system app && useradd --system --gid app --no-create-home app
COPY --from=build /app/bootstrap/build/libs/*.jar app.jar
USER app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]