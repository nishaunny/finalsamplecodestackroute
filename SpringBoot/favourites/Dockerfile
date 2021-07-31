FROM openjdk:16
ADD target/favourites-0.0.1-SNAPSHOT.jar favourites-0.0.1-SNAPSHOT.jar
ENV MONGO_URL=mongodb://localhost:27017/favourites
EXPOSE 8089
ENTRYPOINT ["java","-jar","favourites-0.0.1-SNAPSHOT.jar"]