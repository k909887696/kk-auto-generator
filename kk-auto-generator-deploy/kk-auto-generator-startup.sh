#!/bin/bash
export JAVA_HOME=/application/java/jdk1.8.0_111
export JRE_HOME=/application/java/jdk1.8.0_111/jre
export PATH=$JAVA_HOME/bin/:$PATH
nohup java -Xms256M -Xmx512M -jar /application/kk-auto-generator/kk-auto-generator-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
