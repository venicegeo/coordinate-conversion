#! /bin/bash -ex


cd coord-convert
grails compile
grails -Dbuild.compiler=javac1.7 build-standalone pzsvc-coordinate-conversion.jar
