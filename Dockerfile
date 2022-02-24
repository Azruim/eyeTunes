FROM openjdk:17
ADD build/libs/eyeTunes-0.0.1-SNAPSHOT.jar eye-tunes.jar
ENTRYPOINT ["java", "-jar", "eye-tunes.jar"]
