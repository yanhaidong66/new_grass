FROM alpine
LABEL maintainer="hd" 
LABEL description="alpine,jdk21,oauth"
RUN apk add --no-cache openjdk21

ENV ENV JAVA_HOME=/usr/lib/jvm/default-jvm
WORKDIR /app
COPY target/oauth-jar-with-dependencies.jar /app/app.jar

EXPOSE 9001
CMD ["java", "-jar", "app.jar"]
