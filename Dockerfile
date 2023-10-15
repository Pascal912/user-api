FROM eclipse-temurin:17-jdk-alpine
MAINTAINER pascaljahn.be@gmail.com
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]