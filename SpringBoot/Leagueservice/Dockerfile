FROM openjdk:16
ADD target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
ENV MONGO_URL=mongodb://localhost:27017/scoreupleagueinfo
EXPOSE 9095
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]