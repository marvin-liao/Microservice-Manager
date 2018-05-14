FROM openjdk
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ADD target/customer-service.jar customer-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-jar", "/customer-service.jar"]
EXPOSE 3333