FROM openjdk:8

VOLUME /tmp

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ARG WAIT_FOR_IT
COPY ${WAIT_FOR_IT} ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

CMD ./wait-for-it.sh -t 60 postgres:5432 -- java -jar app.jar