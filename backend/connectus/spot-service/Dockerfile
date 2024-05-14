FROM openjdk:17

ARG JAR_FILE=build/libs/spot-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /spot-service.jar

EXPOSE 8103

ENTRYPOINT ["java","-jar", "/spot-service.jar"]
