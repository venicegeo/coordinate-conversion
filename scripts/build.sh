#! /bin/bash -ex

pwd
echo $PATH
cd coord-convert
grails prod build-standalone pzsvc-coordinate-conversion.jar
