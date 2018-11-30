import sbt.Keys.{resolvers, testFrameworks}
import scalajsbundler.util.JSON._

resolvers in ThisBuild += Opts.resolver.sonatypeReleases
resolvers in ThisBuild += Opts.resolver.sonatypeSnapshots

scalaVersion in ThisBuild := "2.12.7"

val pversion = "0.1"

version in ThisBuild := {
  if (sys.props.contains("TRAVIS_BUILD_NUMBER")) {
    s"$pversion.${sys.props("TRAVIS_BUILD_NUMBER")}"
  } else {
    pversion
  }
}

lazy val `idealingua-sjs` = (project in file("idealingua-sjs"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    libraryDependencies += "com.github.pshirshov.izumi.r2" %%% "idealingua-core" % "0.7.0-SNAPSHOT",
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.7.1",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6",
    version in webpack := "4.1.1",
    webpackConfigFile in fastOptJS := Some(baseDirectory.value / "dev.webpack.config.js"),
    webpackConfigFile in fullOptJS := Some(baseDirectory.value / "prod.webpack.config.js"),
    webpackConfigFile in Test := Some(baseDirectory.value / "common.webpack.config.js")
  )
  .settings(
    inConfig(Compile)(Seq(additionalNpmConfig in Compile := Map(
      "name" -> str(name.value),
      "version" -> str(version.value),
      "description" -> str("Idealingua parser and typer"),
      "license" -> str("BSD-3-Clause"),
      "repository" -> obj(
        "type" -> str("git"),
        "url" -> str("https://github.com/pshirshov/idealingua-scalajs-experiment")
      )
    )))

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


name := "idealingua-scalajs"
