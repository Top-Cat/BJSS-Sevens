FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle clean build --no-daemon

FROM openjdk:8-jre-slim
RUN mkdir /app
COPY --from=build /src/build/libs/*.jar /app/
WORKDIR /app

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","BJSS-Sevens-1.0-SNAPSHOT.jar"]
