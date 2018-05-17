#!/bin/sh

nohup /shell/agent.sh>&1 2>&1 &

/usr/local/bin/docker-entrypoint.sh mysqld