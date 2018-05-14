#!/bin/bash

mvn clean

docker rm $(docker ps -a -q)
