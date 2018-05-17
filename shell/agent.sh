#!/bin/bash

while true
do
    C_HOST=`awk 'END{print $2}' /etc/hosts`
    C_NAME=$CONTAINER_NAME
    C_IMAGE=$CONTAINER_IMAGE
    C_IP=`awk 'END{print $1}' /etc/hosts`
    C_TIME=`date "+%Y-%m-%d %H:%M:%S"`

    C_JSON="{\"host\":\"$C_HOST\", \"name\":\"$C_NAME\", \"ip\":\"$C_IP\", \"image\":\"$C_IMAGE\", \"time\":\"$C_TIME\"}"
    echo $C_JSON
    curl -X POST --header 'Content-Type: application/json' -d "$C_JSON" 'http://MICRO-MGR:8080/machine/publish'
    sleep 5s
done