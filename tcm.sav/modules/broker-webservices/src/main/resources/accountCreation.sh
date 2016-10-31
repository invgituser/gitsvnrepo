#!/bin/sh

LOG=/home/abhangp/broker/log
LIB_HOME=/home/abhangp/broker
LOG_HOME=/home/abhangp/broker/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

/opt/jdk1.7.0_65/bin/java -cp ${LIB_HOME}/broker-webservices-1.0-SNAPSHOT.jar com.invessence.MainPendingAccCreation > ${LOG_HOME}/acctCreation.log 2>&1 &
