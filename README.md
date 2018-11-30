# ScalaJS playground for Idealingua Core 

Before you start
----------------
```
npm install jsdom
```


You may want to install `izumi-r2` artifacts locally:

```
cd ~/work/izumi-r2
sbt clean publishLocal
```

**Important**: this example is based on scalajs 0.6/sbt 0.13, it requires java 8 to build.


Build
-----

```
sbt clean fastOptJS fullOptJS 
```

Production output:

```
./idealingua-sjs/target/scala-2.12/idealingua-sjs-jsdeps.js
./idealingua-sjs/target/scala-2.12/idealingua-sjs-opt.js
```

Testing
-------

```
sbt clean test
```

Test Application
----------------

Fast optimized:

```
sbt clean fastOptJS && open example-fastopt.html
```

Full optimized:

```
sbt clean fullOptJS && open example-opt.html
```


### Console experiments

Just the global `Idealingua` object, without UI/jQuery 

```
sbt clean fastOptJS && open example-fastopt-console.html
sbt clean fullOptJS && open example-opt-console.html
```
