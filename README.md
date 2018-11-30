# ScalaJS playground for Idealingua Core 

Before you start:

```
npm install jsdom
```


You may want to install `izumi-r2` artifacts locally:

```
cd ~/work/izumi-r2
sbt clean publishLocal
```

Then:

```
sbt clean fastOptJS && open example-fastopt.html
```

For full optimization:

```
sbt clean fullOptJS && open example-opt.html
```

Important: this example is based on scalajs 0.6/sbt 0.13, it requires java 8 to build.
