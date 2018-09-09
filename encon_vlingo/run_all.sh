#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

./gradlew build --console=plain
./gradlew run --console=plain &
elixir --sname mike@localhost mike.exs
