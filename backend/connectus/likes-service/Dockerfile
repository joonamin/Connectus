FROM openjdk:17

ARG JAR_FILE=build/libs/like-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /like-service.jar

EXPOSE 8108

ENTRYPOINT ["java","-jar" "/like-service.jar"]
