FROM openjdk:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 80:1107
ENTRYPOINT ["java","-jar","/app.jar"]
