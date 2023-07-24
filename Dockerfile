FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/festival-0.0.1-SNAPSHOT.jar festival-servidor.jar
ENTRYPOINT ["java","-jar","/festival-servidor.jar"]