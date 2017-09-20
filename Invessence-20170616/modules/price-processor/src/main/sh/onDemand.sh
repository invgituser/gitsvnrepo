#!/bin/sh

LIB_HOME=/home/abhangp/price
LOG_HOME=/home/abhangp/price/log
JAVA_HOME=/opt/jdk1.7.0_65/bin/java

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

${JAVA_HOME} -cp ${LIB_HOME}/price-processor-1.0-SNAPSHOT.jar com.invessence.price.processor.MainOnDemand > ${LOG_HOME}/price.log 2>&1 &

