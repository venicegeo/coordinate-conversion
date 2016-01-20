#! /bin/bash -ex

# sdk
curl -s get.sdkman.io | bash
echo "sdkman_auto_answer=true" >> ~/.sdkman/etc/config
source ~/.sdkman/bin/sdkman-init.sh

# groovy
sdk install groovy 2.3.7

# grails
sdk install grails 2.5.0
