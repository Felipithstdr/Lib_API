FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/biblioteca-senai-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} biblioteca-senai-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/biblioteca-senai-0.0.1-SNAPSHOT.jar"]