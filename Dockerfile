FROM openjdk:8-jdk-alpine

RUN mkdir /mobile-app-ws

WORKDIR /mobile-app-ws

COPY target/mobile-app-ws-0.0.1-SNAPSHOT.jar /mobile-app-ws/mobile-app-ws-0.0.1-SNAPSHOT.jar

CMD ["sh", "-c", "java -jar mobile-app-ws-0.0.1-SNAPSHOT.jar"]
