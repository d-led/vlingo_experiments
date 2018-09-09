#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

gradle build --console=plain
gradle run --console=plain &
elixir --sname mike@localhost mike.exs
