FROM openjdk:11-jdk-slim
EXPOSE 8080
ADD /target/northwind-service.jar /northwindproject/northwind-service.jar
ENTRYPOINT [ "sh", "-c", "java -jar /northwindproject/northwind-service.jar" ]