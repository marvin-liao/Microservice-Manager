FROM java:8-jre
MAINTAINER liaofanding <marvin.liao@qq.com>

ADD ./target/DocCenter-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-Dspring.profiles.active=container", "-jar", "/app/DocCenter-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080