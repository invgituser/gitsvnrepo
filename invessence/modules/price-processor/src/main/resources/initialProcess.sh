#!/bin/sh

LIB_HOME=/home/abhangp/price
LOG_HOME=/home/abhangp/price/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

/opt/jdk1.7.0_65/bin/java -cp ${LIB_HOME}/price-processor-1.0-SNAPSHOT.jar com.invessence.price.processor.InitialProcess > ${LOG_HOME}/price.log 2>&1 &

