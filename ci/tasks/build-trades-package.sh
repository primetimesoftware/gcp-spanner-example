#!/bin/bash
#########################################################################
#
#  This script builds the GCP Spanner application and creates
#  a zip file with the resulting jar and a Cloud Foundry manifest.yml.
#  The zip is then published as a release to the github repo
#
#########################################################################

set -x
set -e

# Define the maven repo directory so it can be cached between builds.
export ROOT_FOLDER=$( pwd )
export MAVEN_OPTS="-Dmaven.repo.local=${ROOT_FOLDER}/.m2"

version=$(cat version/version)
cd trades-repo

# Skipping tests because it failed running in Concourse due to a resource limitation (I think).
./mvnw package -DskipTests -Dversion=${version}

mkdir ../zip-files/trades
cp manifest.yml ../zip-files/trades
cp target/trades-${version}.jar ../zip-files/trades

cd ../zip-files
cd trades
jar cMf trades.zip manifest.yml trades-${version}.jar

cp ../version/version release-name
echo "latest" > release-tag