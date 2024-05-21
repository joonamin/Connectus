FROM openjdk:17

ARG JAR_FILE=build/libs/apigateway-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /apigateway-service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/apigateway-service.jar"]
