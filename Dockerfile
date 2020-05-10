FROM adoptopenjdk/openjdk11:alpine-slim
MAINTAINER maxiplux <maxiplux@gmail.com>

RUN apk update && apk upgrade && apk add bash

EXPOSE 8080

COPY --from=target/b2bcart-0.0.1-SNAPSHOT.jar /app.jar

ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -jar /app.jar
