FROM openjdk:17

ARG JAR_FILE=build/libs/discovery-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /discovery-service.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar" "/discovery-service.jar"]
