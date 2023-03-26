FROM --platform=linux/amd64 openjdk:8-jdk

EXPOSE 8080
VOLUME /tmp

COPY target/*.jar /start-up.jar

ENV JAVA_OPTS=""

CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /start-up.jar" ]
