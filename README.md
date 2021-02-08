# vlingo experiments

[![Build Status](https://travis-ci.com/d-led/vlingo_experiments.svg?branch=master)](https://travis-ci.com/d-led/vlingo_experiments)

- Vlingo Actor Model Platform: [https://vlingo.io](https://vlingo.io)
- Example apps:
  - [vaughnvernon/DDDWithActors](https://github.com/VaughnVernon/DDDwithActors) example using vlingo (no persistence) : [ddd_actors_vlingo](ddd_actors_vlingo), including various ways to test the actors: [ddd_actors_vlingo/src/test/java](ddd_actors_vlingo/src/test/java)
  - Malicious actors monopolizing their scheduler: [ticker_stress](ticker_stress)
  - Interop between Actors and Rx: batching incoming data via [RxKotlin](https://github.com/ReactiveX/RxKotlin), in [Kotlin](https://kotlinlang.org): [batching_with_rx](batching_with_rx)
  - A vlingo node acting as an Erlang node, waits for a connection from an Elixir node, and re-enacts the beginning of [Erlang, the movie](https://www.youtube.com/watch?v=xrIjfIjssLE) via [appulse-projects/encon-java](https://github.com/appulse-projects/encon-java): [encon_vlingo](encon_vlingo)
  - A vlingo actor listening to a [ZeroMQ](http://zeromq.org) socket, dispatching the messages to other actors. Here, a client in [Python](http://pyzmq.readthedocs.io): [vlingo_0mq_python](vlingo_0mq_python)
- Meetup talk slides [(pdf)](presentations/2018_ljug_lightweight_java_actors_vlingo_ledentsov.pdf) as presented at the Lightweight Java User Group Munich: [Actor Model. Concurrency That Feels Like Home](https://www.meetup.com/Lightweight-Java-User-Group-Munchen/events/248063316/)
