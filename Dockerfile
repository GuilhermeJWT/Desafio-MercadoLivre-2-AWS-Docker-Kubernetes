FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080

ADD target/mercado-livre-0.0.1-SNAPSHOT.jar mercado-livre-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "jar", "mercado-livre-0.0.1-SNAPSHOT.jar"]

#URL: https://hub.docker.com/repository/docker/guilhermesantosdocker/mercado-livre