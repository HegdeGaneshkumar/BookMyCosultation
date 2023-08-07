FROM openjdk:17-jdk-alpine
MAINTAINER upgrad
ADD ./target/bmc-gateway-0.0.1-SNAPSHOT.jar /usr/src/app/bmc-gateway-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 9191
ENTRYPOINT [ "java", "-jar", "/usr/src/app/bmc-gateway-0.0.1-SNAPSHOT.jar"]
