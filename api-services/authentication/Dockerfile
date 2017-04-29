FROM frolvlad/alpine-oraclejdk8:slim
ADD ./build/libs/authentication-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
