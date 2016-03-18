#!/bin/bash -ex

pushd `dirname $0`/.. > /dev/null
root=$(pwd -P)
popd > /dev/null

if ! type sdk; then 
  curl -s get.sdkman.io | bash
  echo "sdkman_auto_answer=true" >> ~/.sdkman/etc/config
  source ~/.sdkman/bin/sdkman-init.sh
fi

# groovy
sdk install groovy 2.4.5

# grails
sdk install grails 2.5.4
