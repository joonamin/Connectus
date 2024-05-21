FROM openjdk:17

ARG JAR_FILE=build/libs/post-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /post-service.jar

EXPOSE 8101

ENTRYPOINT ["java","-jar", "/post-service.jar"]
