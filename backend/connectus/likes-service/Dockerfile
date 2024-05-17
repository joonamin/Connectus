FROM openjdk:17

ARG JAR_FILE=build/libs/likes-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /likes-service.jar

EXPOSE 8108

ENTRYPOINT ["java","-jar", "/likes-service.jar"]
