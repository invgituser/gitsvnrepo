#!/bin/sh

LOG=/inv/log
JAVA_HOME=/usr/bin
LIB_HOME=/inv/services/lib
LOG_HOME=/inv/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

nohup java -cp ${LIB_HOME}/complete-brokerservices-1.0-SNAPSHOT.jar com.invessence.broker.BrokerFilesProcessor > ${LOG_HOME}/broker.log 2>&1 &


