#!/bin/sh

nohup /shell/agent.sh>/dev/null 2>&1 &

/usr/local/bin/docker-entrypoint.sh mysqld