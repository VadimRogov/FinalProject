FROM openjdk:latest
COPY *.java /src/
RUN mkdir /app && ls /src && javac /src/PrintXmxXms.java -d /app
ENV JAVA_OPTS=""
CMD ["sh", "-c", "java -version && java $JAVA_OPTS -cp /app PrintXmxXms"]