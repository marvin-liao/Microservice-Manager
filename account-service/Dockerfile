FROM openjdk
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ADD target/account-service.jar account-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-jar", "/account-service.jar"]
EXPOSE 2222