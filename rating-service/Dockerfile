FROM openjdk:17-jdk-alpine
MAINTAINER upgrad
ADD ./target/rating-service-0.0.1-SNAPSHOT.jar /usr/src/app/rating-service-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8084
ENTRYPOINT [ "java", "-jar", "/usr/src/app/rating-service-0.0.1-SNAPSHOT.jar"]
