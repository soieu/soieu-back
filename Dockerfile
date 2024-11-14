FROM openjdk:21-jdk-oracle
ARG JAR_FILE=/build/libs/soieu-back-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY ./application.yml /src/main/resources/
ENTRYPOINT ["java","-jar","/app.jar"]