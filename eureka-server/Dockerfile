FROM openjdk:17-jdk-alpine
MAINTAINER upgrad
ADD ./target/eureka-server-0.0.1-SNAPSHOT.jar /usr/src/app/eureka-server-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "/usr/src/app/eureka-server-0.0.1-SNAPSHOT.jar"]
