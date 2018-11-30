import sbt.Keys.{resolvers, testFrameworks}

resolvers in ThisBuild += Opts.resolver.sonatypeReleases
resolvers in ThisBuild += Opts.resolver.sonatypeSnapshots

scalaVersion in ThisBuild := "2.12.7"

lazy val `idealingua-sjs` = (project in file("idealingua-sjs"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies += "com.github.pshirshov.izumi.r2" %%% "idealingua-core" % "0.7.0-SNAPSHOT",
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.7.1",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6"
  )

lazy val `idealingua-sjs-test` = (project in file("idealingua-sjs-test"))
  .dependsOn(`idealingua-sjs`)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % "test",
    libraryDependencies += "org.querki" %%% "jquery-facade" % "1.2",

    jsDependencies += "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js",

    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalaJSUseMainModuleInitializer := true,
    skip in packageJSDependencies := false,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )

lazy val root = (project in file("."))
  .aggregate(`idealingua-sjs`, `idealingua-sjs-test`)
  .enablePlugins(ScalaJSPlugin)


name := "idealingua-scalajs-example"
