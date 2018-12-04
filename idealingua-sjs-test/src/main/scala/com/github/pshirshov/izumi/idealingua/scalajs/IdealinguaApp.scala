package com.github.pshirshov.izumi.idealingua.scalajs

import com.github.pshirshov.izumi.idealingua.model.output.{Module, ModuleId}
import com.github.pshirshov.izumi.idealingua.translator.ProvidedRuntime
import org.querki.jquery._
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.JSON
import Codecs._

object IdealinguaApp {

  def main(args: Array[String]): Unit = {
    $(() => setupUI())
  }

  val domain: String =
    """
      |domain idltest.enums
      |
      |enum ShortSyntaxEnum = Element1 | Element1
    """.stripMargin

  val example: Map[String, String] = Map("idltest/enums.domain" -> domain)

  def setupUI(): Unit = {
    $("""<div id="input-outer"/>""")
      .appendTo($("body"))

    $("""<div id="output-outer"/>""")
      .appendTo($("body"))

    $("""<div id="controls-outer"/>""")
      .appendTo($("body"))

    $("""<textarea id="input" rows="15" cols="100" />""")
      .text(JSON.stringify(example.toJSDictionary))
      .appendTo($("#input-outer"))

    $("""<textarea id="output" rows="15" cols="100" />""")
      .appendTo($("#output-outer"))

    $("""<button id="parse-fs" type="button">Parse as FS json</button>""")
      .click(() => parseFsJson())
      .appendTo($("#controls-outer"))

    $("""<button id="parse-fs" type="button">Compile as FS json</button>""")
      .click(() => compileFsJson())
      .appendTo($("#controls-outer"))

    $(
      """<select id="target-language">
        | <option value="scala">scala</option>
        | <option value="go">go</option>
        | <option value="csharp">csharp</option>
        | <option value="typescript">typescript</option>
        |</select>""".stripMargin)
      .appendTo($("#controls-outer"))



    $("""<button id="parse-fs" type="button">ProvidedRuntime example</button>""")
      .click(() => runtimeExample())
      .appendTo($("#controls-outer"))
  }

  def runtimeExample(): Unit = {
    val runtime = ProvidedRuntime(Seq(
      Module(ModuleId(Seq("path", "to", "directory1"), "file1"), "other content")
      , Module(ModuleId(Seq("path", "to", "directory2"), "file2"), "other content")
    ))

    $("#output").text(write(runtime, indent = 2))
  }

  def compileFsJson(): Unit = {
    val input = $("#input").text()
    val fs = JSON.parse(input).asInstanceOf[js.Dictionary[String]]
    val language = $("#target-language").value()
    val parsed = IdealinguaJSImport.compilePseudoFS(fs, language.toString, js.Dictionary[js.Any](), js.Dictionary[js.Any](), js.Array("*"))

    val asJsonStr = JSON.stringify(parsed)
    val asJson = read[ujson.Value](asJsonStr)
    val idented = write(asJson, indent = 2)
    $("#output").text(idented)
  }

  def parseFsJson(): Unit = {
    val input = $("#input").text()
    val fs = JSON.parse(input).asInstanceOf[js.Dictionary[String]]
    val parsed = IdealinguaJSImport.parsePseudoFS(fs)
    val asJsonStr = JSON.stringify(parsed)
    val asJson = read[ujson.Value](asJsonStr)
    val idented = write(asJson, indent = 2)
    $("#output").text(idented)
  }
}
