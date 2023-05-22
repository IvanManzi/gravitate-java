FROM openjdk:17-jdk
COPY target/content_manager_service-1.0-SNAPSHOT.jar /app.jar
COPY src/main/resources/application.yml /application.yml
EXPOSE 1072
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=?Mfptnj123@hview2022
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/gravitatedb
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8762/eureka
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.name=application","-Dspring.config.location=/","-jar","/app.jar"]
