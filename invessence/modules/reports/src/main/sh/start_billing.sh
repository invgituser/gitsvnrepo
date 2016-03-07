#!/bin/sh

LOG=/inv/log
JAVA_HOME=/usr/bin
LIB_HOME=/inv/services/lib
LOG_HOME=/inv/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

nohup java -cp ${LIB_HOME}/complete-reports-1.0-SNAPSHOT.jar com.invessence.report.billing.BillingStatementsGenerator > ${LOG_HOME}/billing.log 2>&1 &
