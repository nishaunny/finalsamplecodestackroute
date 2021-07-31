FROM openjdk:16
ADD target/userauthenticationapp-0.0.1-SNAPSHOT.jar userauthenticationapp-0.0.1-SNAPSHOT.jar
ENV MYSQL_URL=jdbc:mysql://localhost:3306/ustauthdbse?createDatabaseIfNotExist=true&user=root&password=pass
ENV MYSQL_ROOT_PASSWORD=pass
EXPOSE 9095
ENTRYPOINT ["java","-jar","userauthenticationapp-0.0.1-SNAPSHOT.jar"]