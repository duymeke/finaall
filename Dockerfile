FROM eclipse-temurin:21-jdk
MAINTAINER duymeke
COPY build/libs/finaall-0.0.1-SNAPSHOT.jar finaall.jar
ENTRYPOINT ["java", "-jar", "finaall.jar"]