#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

./gradlew build --console=plain

python --version
pip install --user zmq

echo "starting the client in the background"
python client.py > /dev/null 2>&1 &

echo "running the demo"
./gradlew run --console=plain
