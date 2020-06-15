FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9091
ADD target/skills-tracker-0.0.1-SNAPSHOT.jar skills-tracker.jar
ENTRYPOINT ["java","-jar","skills-tracker.jar"]