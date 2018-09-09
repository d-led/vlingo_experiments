#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

TRAVIS_BUILD_DIR=${TRAVIS_BUILD_DIR:-`pwd`}

echo "--=== ticker_stress ===--"
cd ${TRAVIS_BUILD_DIR}/ticker_stress
./gradlew run --console=plain

echo "--=== ddd_actors_vlingo ===--"
cd ${TRAVIS_BUILD_DIR}/ddd_actors_vlingo
./gradlew test run --console=plain --stacktrace

echo "--=== batching_with_rx ===--"
cd ${TRAVIS_BUILD_DIR}/batching_with_rx
./gradlew run --console=plain --stacktrace
elixir --version

echo "--=== encon_vlingo ===--"
cd ${TRAVIS_BUILD_DIR}/encon_vlingo
./run_all.sh
