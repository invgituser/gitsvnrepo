#!/bin/sh

LOG=/inv/log
JAVA_HOME=/usr/bin
LIB_HOME=/inv/services/lib
LOG_HOME=/inv/log

export CLASSPATH=${CLASSPATH}:${LIB_HOME}

for jlist in "`ps -eaf | grep -i emailer | grep -v grep`"
do
	id=`echo $jlist | gawk -F' ' '{print $2}'`
	echo kill $id
	kill -9 $id
done

