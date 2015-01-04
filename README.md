Availability Calculator
==============

Calculates predicted system availability for given:
* number of nodes
* required number of nodes that response successfully to request
* each node's availability

Based on [Bernoulli process](http://en.wikipedia.org/wiki/Bernoulli_process).

Running
=======

```
sbt ~fastOptJS
```

Open your browser at http://localhost:12345/target/scala-2.11/classes/index-dev.html
