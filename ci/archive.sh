#!/bin/bash -ex

pushd `dirname $0`/.. > /dev/null
root=$(pwd -P)
popd > /dev/null

# gather some data about the repo
source $root/ci/vars.sh
uname -a
lsb_release -a
! type grails >/dev/null 2>&1 && source $root/ci/grails.sh

pushd $root/coord-convert > /dev/null
  grails compile
  grails -Dbuild.compiler=javac1.7 build-standalone $root/$APP.$EXT
popd > /dev/null
