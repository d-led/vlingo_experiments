# vlingo experiments

[![Build Status](https://travis-ci.org/d-led/vlingo_experiments.svg?branch=master)](https://travis-ci.org/d-led/vlingo_experiments)

- Vlingo Actor Model Platform: https://vlingo.io
- Example apps:
  - [vaughnvernon/DDDWithActors](https://github.com/VaughnVernon/DDDwithActors) example using vlingo (no persistence) : [ddd_actors_vlingo](ddd_actors_vlingo), including various ways to test the actors: [ddd_actors_vlingo/src/test/java](ddd_actors_vlingo/src/test/java)
  - Malicious actors monopolizing their scheduler: [ticker_stress](ticker_stress)
  - Interop between Actors and Rx: batching incoming data via [RxKotlin](https://github.com/ReactiveX/RxKotlin), in [Kotlin](https://kotlinlang.org): [batching_with_rx](batching_with_rx)
  - A vlingo node acting as an Erlang node, waits for a connection from an Elixir node, and re-enacts the beginning of [Erlang, the movie](https://www.youtube.com/watch?v=xrIjfIjssLE): [encon_vlingo](encon_vlingo)
