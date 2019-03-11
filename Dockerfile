FROM openjdk:latest
EXPOSE 8888
COPY ./target/BTeamCoursework.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "BTeamCoursework.jar", "db:3306"]
