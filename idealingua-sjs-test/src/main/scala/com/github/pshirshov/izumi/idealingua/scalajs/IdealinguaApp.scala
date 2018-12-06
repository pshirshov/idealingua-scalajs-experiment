package com.github.pshirshov.izumi.idealingua.scalajs

import com.github.pshirshov.izumi.idealingua.model.output.{Module, ModuleId}
import com.github.pshirshov.izumi.idealingua.translator.ProvidedRuntime
import org.querki.jquery._
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.{Dictionary, JSON}
import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Codecs._
import ujson.Value

object IdealinguaApp {

  def main(args: Array[String]): Unit = {
    $(() => setupUI())
  }

  val domain: String =
    """
      |domain idltest.enums
      |
      |enum ShortSyntaxEnum = Element1 | Element2
      |enum ShortSyntaxEnum = Element1 | Element2
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
      .value(JSON.stringify(example.toJSDictionary))
      .appendTo($("#input-outer"))

    $("""<textarea id="output" rows="15" cols="100" />""")
      .appendTo($("#output-outer"))

    $("""<button id="parse-fs" type="button">Parse as FS json</button>""")
      .click(() => parseFsJson())
      .appendTo($("#controls-outer"))

    $("""<button id="parse-fs" type="button">Compile as FS json</button>""")
      .click(() => compileFsJson())
      .appendTo($("#controls-outer"))

    $("""<button id="parse-fs" type="button">Pretty-print</button>""")
      .click(() => prettyPrint())
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

  def compileFsJson(): Unit = {
    runSafe {
      input =>
        val fs = asJson(input)
        val language = $("#target-language").value()
        val parsed = IdealinguaJSImport.compilePseudoFS(fs, language.toString, js.Dictionary[js.Any](), js.Dictionary[js.Any](), js.Array("*"))

        prettyJson(parsed)
    }
  }

  def parseFsJson(): Unit = {
    runSafe {
      input =>
        val fs = asJson(input)
        val parsed = IdealinguaJSImport.parsePseudoFS(fs)
        prettyJson(parsed)
    }
  }

  def prettyPrint(): Unit = {
    runSafe {
      input =>
        val fs = asJson(input)
        val parsed = IdealinguaJSImport.prettyPrintDomains(fs)
        prettyJson(parsed)
    }
  }

  def runtimeExample(): Unit = {
    runSafe {
      _ =>
        val runtime = ProvidedRuntime(Seq(
          Module(ModuleId(Seq("path", "to", "directory1"), "file1"), "other content")
          , Module(ModuleId(Seq("path", "to", "directory2"), "file2"), "other content")
        ))
        write(runtime, indent = 2)
    }
  }

  private def asJson(input: String): Dictionary[String] = {
    JSON.parse(input).asInstanceOf[Dictionary[String]]
  }

  private def runSafe(f: String => String): Unit = doSafe {
    _ => {
      val input = $("#input").value().toString
      println(s"Input: $input")
      $("#output").value(f(input))
    }
  }

  private def doSafe[T](f: Unit => Unit): Unit = {
    try {
      f()
    } catch {
      case t: Throwable =>
        $("#output").value(t.toString)
    }
  }

  private def prettyJson(parsed: js.Object): String = {
    val asJsonStr = JSON.stringify(parsed)
    val asJson = read[Value](asJsonStr)
    val indented = write(asJson, indent = 2)

    indented
  }
}
