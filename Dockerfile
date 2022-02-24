FROM gradle:jdk17-alpine as gradle
WORKDIR /app
COPY . .
RUN gradle bootJar



FROM openjdk:17
COPY --from=gradle app/build/libs/*.jar app/app.jar
ENV PORT 8080
ENV SPRING_PROFILE production
RUN chown -R 1000:1000 /app
USER 1000:1000
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "-Dspring.profiles.active=${SPRING_PROFILE}", "app/app.jar"]
