FROM openjdk-8
EXPOSE 8080
ADD target/mobile-app-ws-0.0.1-SNAPSHOT.jar mobile-app-ws-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "mobile-app-ws-0.0.1-SNAPSHOT.jar"]

