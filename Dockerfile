FROM openjdk:11-jdk-slim
ADD target/springbootdockerdeploy-backendauth.jar springbootdockerdeploy-backendauth.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/springbootdockerdeploy-backendauth.jar"]