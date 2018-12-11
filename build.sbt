import sbt.Keys.{libraryDependencies, resolvers, testFrameworks}
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport.npmDependencies
import scalajsbundler.util.JSON._

resolvers in ThisBuild += Resolver.defaultLocal
resolvers in ThisBuild += Opts.resolver.sonatypeReleases
resolvers in ThisBuild += Opts.resolver.sonatypeSnapshots

scalaVersion in ThisBuild := "2.12.8"

val pversion = "0.1"

version in ThisBuild := {
  if (sys.env.contains("TRAVIS_BUILD_NUMBER")) {
    s"$pversion.${sys.env("TRAVIS_BUILD_NUMBER")}"
  } else {
    s"$pversion.0"
  }
}

lazy val `idealingua-sjs` = (project in file("idealingua-sjs"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.7.1"
    , libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6"
    , libraryDependencies += "org.scala-lang.modules" %%% "scala-collection-compat" % "0.2.1"
    , libraryDependencies += "com.github.pshirshov.izumi.r2" %%% "idealingua-transpilers" % "0.7.0-SNAPSHOT"
    , webpackNodeArgs in Compile ++= Seq("--max_old_space_size=4096")
    // fails. Sbt bug?
    , additionalNpmConfig in Compile ++= Map(
      "name" -> str("idealingua-js-facade"),
      "version" -> str(version.value),
      "description" -> str("Idealingua parser and typer"),
      "license" -> str("EULA"),
      "main" -> str("idealingua-sjs-opt.js"),
      "runkitExample" -> str(
        s"""var idl = require("idealingua-js-facade");
           |
           |idl.Idealingua.parsePseudoFS({"idltest/enums.domain": "\\ndomain idltest.enums\\n\\nenum ShortSyntaxEnum = Element1 | Element2\\n    \"});
           |
         """.stripMargin),
      "repository" -> obj(
        "type" -> str("git"),
        "url" -> str("https://github.com/pshirshov/idealingua-scalajs-experiment")
      )
    )
  )

lazy val `idealingua-sjs-test` = (project in file("idealingua-sjs-test"))
  .dependsOn(`idealingua-sjs`)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % "test",
    libraryDependencies += "org.querki" %%% "jquery-facade" % "1.2",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalaJSUseMainModuleInitializer := true,
    skip in packageJSDependencies := false,

    jsDependencies += "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js",
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv() //org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv.Config().withArgs(List()))
  )

lazy val root = (project in file("."))
  .aggregate(`idealingua-sjs`, `idealingua-sjs-test`)


name := "idealingua-scalajs"
