FROM openjdk:17-jdk
COPY target/content_manager_service-1.0-SNAPSHOT.jar /app.jar
COPY src/main/resources/application.yml /application.yml
EXPOSE 1072
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application","-Dspring.config.location=/","-jar","/app.jar"]
