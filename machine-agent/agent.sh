#!/bin/bash

while true
do
    v_name=`uname -a`
    v_time=`date "+%Y-%m-%d %H:%M:%S"`
    v_json="{\"machine\":\"$v_name\", \"time\":\"$v_time\"}"
    curl -X POST --header 'Content-Type: application/json' -d "${v_json}" 'http://MICRO-MGR:8080/machine/publish'
    sleep 5s
done