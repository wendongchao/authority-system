# 基础镜像信息
FROM openjdk:8-jdk-alpine
# 维护者信息
MAINTAINER wendongchao

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD authority-system-1.0-SNAPSHOT.jar authority-system.jar

ENTRYPOINT ["java", "-jar", "authority-system.jar"]