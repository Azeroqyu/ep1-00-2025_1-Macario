#!/usr/bin/env bash
cd ./src
javac -cp ../bin -d ../bin *.java 
cd ..
sh run.sh
