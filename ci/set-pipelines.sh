#!/bin/bash

set -e

if [ "$1" = "" ]; then
  echo $0: usage: $0 target
  exit
fi

target=$1
this_directory=`dirname "$0"`

fly -t ${target} set-pipeline -p trades-app -c ${this_directory}/trades-pipeline.yml
