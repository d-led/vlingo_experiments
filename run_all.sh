#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

TRAVIS_BUILD_DIR=${TRAVIS_BUILD_DIR:-`pwd`}

echo "--=== ticker_stress ===--"
cd ${TRAVIS_BUILD_DIR}/ticker_stress
./gradlew clean run --console=plain

echo "--=== ddd_actors_vlingo ===--"
cd ${TRAVIS_BUILD_DIR}/ddd_actors_vlingo
./gradlew clean test run --console=plain

echo "--=== batching_with_rx ===--"
cd ${TRAVIS_BUILD_DIR}/batching_with_rx
./gradlew clean run --console=plain --stacktrace
elixir --version

echo "--=== encon_vlingo ===--"
cd ${TRAVIS_BUILD_DIR}/encon_vlingo
./run_all.sh

echo "--=== vlingo_0mq_python ===--"
cd ${TRAVIS_BUILD_DIR}/vlingo_0mq_python
./run_all.sh
