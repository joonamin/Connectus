FROM openjdk:17

ARG JAR_FILE=build/libs/walk-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /walk-service.jar

EXPOSE 8106

ENTRYPOINT ["java","-jar" "/walk-service.jar"]
