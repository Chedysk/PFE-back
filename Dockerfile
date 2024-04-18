FROM openjdk:11-jre-slim
COPY target/ELearningManagement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8085