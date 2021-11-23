FROM adoptopenjdk/openjdk11:latest
#WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} ./
ENTRYPOINT ["java","-jar","/mind-server-0.0.1-SNAPSHOT.jar"]

#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

# WORKDIR 확인
# .jar 파일명칭 변경 방법
# profile 구분 방법