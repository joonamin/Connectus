FROM openjdk:17

ARG JAR_FILE=build/libs/gather-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /gather-service.jar

EXPOSE 8102

ENTRYPOINT ["java","-jar", "/gather-service.jar"]