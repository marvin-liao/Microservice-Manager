#!/bin/bash

nohup /shell/agent.sh>&1 2>&1 &

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar ${APPLICATION_JAR}