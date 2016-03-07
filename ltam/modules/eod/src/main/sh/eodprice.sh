#!/bin/sh

LOG=/inv/log
JAVA_HOME=/usr/bin
LIB_HOME=/inv/services/lib
LOG_HOME=/inv/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

java -jar ${LIB_HOME}/eod-1.0-SNAPSHOT-jar-with-dependencies.jar 
