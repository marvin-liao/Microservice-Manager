FROM openjdk
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ADD target/discovery-service.jar discovery-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-jar", "/discovery-service.jar"]
EXPOSE 8761