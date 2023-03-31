FROM --platform=linux/amd64 openjdk:8-jdk

EXPOSE 8080
VOLUME /tmp

COPY target/*.jar /start-up.jar

ARG SPRING_PROFILES_ACTIVE

ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE

CMD [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Djava.security.egd=file:/dev/./urandom -jar /start-up.jar" ]
