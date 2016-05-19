#!/bin/sh

LOG=/inv/log
JAVA_HOME=/usr/bin
LIB_HOME=/inv/services/lib
LOG_HOME=/inv/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

nohup java -jar ${LIB_HOME}/emailer-1.0-SNAPSHOT-jar-with-dependencies.jar > ${LOG_HOME}/email.log 2>&1 &

