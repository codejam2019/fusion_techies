FROM openjdk:8-jre-alpine
COPY ./target/grabby.war /usr/src/grabby/
WORKDIR /usr/src/grabby
EXPOSE 7070
CMD ["java", "-jar", "grabby.war"]