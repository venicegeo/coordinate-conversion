#! /bin/bash -ex


cd coord-convert
grails compile
grails prod build-standalone pzsvc-coordinate-conversion.jar
