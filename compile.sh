#!/usr/bin/env bash
cd ./src
javac -cp ../bin -d ../bin *.java 
java -cp ../bin Starter
