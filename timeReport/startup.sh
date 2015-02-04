#!/bin/bash
./stop.sh

EXT='-Djava.ext.dirs='
#JDK/JRE目录下的ext目录
EXT=$EXT:'/usr/local/program/jdk1.6.0_43/jre/lib/ext'

#程序引用Jar目录
EXT=$EXT:'./libs'

/usr/local/program/jdk1.6.0_43/jre/bin/java $EXT -jar $* TimingReport.jar &
echo $! > .pid
