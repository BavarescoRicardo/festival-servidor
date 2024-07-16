FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/festival-0.0.1-SNAPSHOT.jar festival-servidor.jar
COPY src/main/resources/certificate.p12 /resources/certificate.p12
ENTRYPOINT ["java","-jar","/festival-servidor.jar"]