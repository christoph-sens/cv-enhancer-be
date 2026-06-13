FROM gradle:9.5.1-jdk25 AS build
WORKDIR /app
COPY . /app/.
RUN ./gradlew clean bootJar --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/build/libs/cvenhancer*.jar /app/cvenhancer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cvenhancer.jar"]