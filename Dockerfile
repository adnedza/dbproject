FROM openjdk:11-jdk-slim
EXPOSE 8080
COPY northwindproject/ northwindproject/
RUN cd northwindproject; ./mvnw -Dmaven.test.skip package
ENTRYPOINT ["java","-jar","/northwindproject/target/northwindproject-0.0.1-SNAPSHOT.jar"]
