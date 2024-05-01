FROM openjdk:17-jdk
LABEL authors="joonamin"
ARG JAR_FILE=build/libs/event-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} event-service.jar

ENTRYPOINT ["java","-jar","/event-service.jar"]