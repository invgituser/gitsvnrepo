export PRJ_HOME=${HOME}/service
export CLASSPATH=${CLASSPATH}:${PRJ_HOME}/lib
export JAVA_EXE=/usr/bin/java
${JAVA_EXE} -jar ${PRJ_HOME}/lib/eod-1.0-SNAPSHOT-jar-with-dependencies.jar
