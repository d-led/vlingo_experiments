#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

./gradlew build --console=plain

echo "--=== starting Joe ===--"
./gradlew run --console=plain &

echo "--=== starting Mike in the Background ===--"
elixir --sname mike@localhost mike.exs &

fg || true
