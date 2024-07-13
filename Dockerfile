FROM maven:3.9.8-amazoncorretto-21-al2023 AS build
WORKDIR /app
COPY . /app/.
RUN ./mvnw clean install

FROM amazoncorretto:21.0.3-al2023-headless
WORKDIR /app
COPY --from=build  /app/target/cvenhancer*.jar /app/cvenhancer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cvenhancer.jar"]
