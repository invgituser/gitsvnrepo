#!/bin/sh

LOG=/inv/log
#JAVA_HOME=/usr/bin
JAVA_HOME=/apps/java/jre1.7.0_80/bin
LIB_HOME=/inv/services/lib/pp
LOG_HOME=/inv/services/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

nohup ${JAVA_HOME}/java -jar ${LIB_HOME}/price-processor-1.0-SNAPSHOT.jar > ${LOG_HOME}/price.log 2>&1 &
