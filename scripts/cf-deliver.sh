#!/bin/bash -ex


# get the current directory
pushd `dirname $0` > /dev/null
base=$(pwd -P)
popd > /dev/null

# gather some data about the repo
source $base/vars.sh

# do we have this artifact in s3? If not, fail.
[ -f $base/../pzsvc-coord-convert.jar ] || { aws s3 ls $S3URL && aws s3 cp $S3URL $base/../pzsvc-coord-convert.jar || exit 1; }

ls -alh
