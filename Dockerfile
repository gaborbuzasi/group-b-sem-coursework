FROM openjdk:latest
COPY ./target/BTeamCoursework-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "BTeamCoursework-0.1.0.2-jar-with-dependencies.jar"]
