FROM openjdk:latest
EXPOSE 8888
COPY ./target/BTeamCoursework-0.1.1.0.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "BTeamCoursework-0.1.1.0.jar", "db:3306"]
