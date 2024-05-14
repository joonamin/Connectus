FROM openjdk:17

ARG JAR_FILE=build/libs/event-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /event-service.jar

EXPOSE 8105

ENTRYPOINT ["java","-jar" "/event-service.jar"]