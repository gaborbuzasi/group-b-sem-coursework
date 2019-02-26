FROM openjdk:latest
COPY ./target/BTeamCoursework-0.1.1.0-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "BTeamCoursework-0.1.1.0-jar-with-dependencies.jar"]
