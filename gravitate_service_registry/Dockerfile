FROM openjdk:17-jdk
COPY gravitate_service_registry/target/gravitate_service_registry-1.0-SNAPSHOT.jar /app.jar
COPY gravitate_service_registry/src/main/resources/application.yml /application.yml
EXPOSE 8762
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application","-Dspring.config.location=/","-jar","/app.jar"]

