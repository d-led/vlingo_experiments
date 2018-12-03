#!/usr/bin/env python
import time
import sys
import zmq

context = zmq.Context()
push = context.socket(zmq.PUSH)
push.connect("tcp://localhost:5678")
count = 10

# initial sleep
time.sleep(5)

for x in range(count):
    push.send_string("Hello from Python " + str(x), zmq.DONTWAIT)
    print(str(x) + " sleeping for a bit")
    time.sleep(1)

push.send_string("stop")
