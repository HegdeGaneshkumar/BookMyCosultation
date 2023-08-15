FROM openjdk:11
MAINTAINER upgrad
ADD ./target/JWTGenerator-0.0.1-SNAPSHOT.jar /usr/src/app/JWTGenerator-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8086
ENTRYPOINT [ "java", "-jar", "/usr/src/app/JWTGenerator-0.0.1-SNAPSHOT.jar"]
